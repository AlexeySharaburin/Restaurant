public class Waiter {

    private Integer dishQuantity = 0;

    public void comeWaiter() {

        String waiterName = Thread.currentThread().getName();
        try {
            System.out.printf("%s пришёл на работу\n", waiterName);

            while (!Thread.interrupted()) {

                Order order;
                Dish dish;

                synchronized (dishQuantity) {
                    if (dishQuantity == Main.dishesMax) {
                        System.out.printf("Так как на кухне закончилсь продукты, %s пошёл наводить порядок\n", waiterName);
                        Thread.currentThread().interrupt();
                    }
                }

                Thread.sleep(3000);
                synchronized (Main.waitingList) {
                    System.out.printf("%s ждёт клиента\n", waiterName);
                    if (Main.waitingList.isEmpty()) {
                        Main.waitingList.wait();
                    }
                    order = Main.waitingList.remove(0);
                }

                synchronized (order) {
                    System.out.printf("%s готов принять заказ\n", waiterName);
                    order.setWaiterName(waiterName);
                    order.notify();
                    order.wait();

                    dish = new Dish();

                    synchronized (dish) {
                        dish.setWaiterName(waiterName);
                        dish.setGuestName(order.getGuestName());
                        synchronized (Main.dishesOrders) {
                            Main.dishesOrders.add(dish);
                            Main.dishesOrders.notify();
                        }

                        if (!dish.readyToBring()) {
                            Thread.sleep(2000);
                            System.out.printf("%s ждёт, пока готовится блюдо для %s\n", waiterName, order.getGuestName());
                            dish.wait();
                        }

                        dish = Main.readyDishes.remove(0);

                        synchronized (dishQuantity) {
                            dishQuantity++;
                        }

                        System.out.printf("%s получил от %s %s\n", waiterName, dish.getCookName(), dish.getDishName());
                        System.out.printf("%s несет %s для %s\n", waiterName, dish.getDishName(), order.getGuestName());

                        synchronized (Main.dishesForGuests) {
                            Main.dishesForGuests.add(dish);
                        }
                    }

                    order.notify();
                    order.wait();

                }
            }
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }
}

