public class Cook {

    private Integer dishQuantity = 0;

    public void cookStart() {
        String cookName = Thread.currentThread().getName();

        try {
            System.out.printf("%s пришёл на работу\n", cookName);

            while (!Thread.interrupted()) {
                Dish dish;

                synchronized (dishQuantity) {
                    if (dishQuantity > Main.dishesMax) {
                        System.out.printf("На кухне закончилсь продукты! %s закончил работать!\n", cookName);
                        break;
                    }
                }

                synchronized (Main.dishesOrders) {
                    if (Main.dishesOrders.isEmpty()) {
                        Main.dishesOrders.wait();
                    }
                    dish = Main.dishesOrders.remove(0);
                }

                System.out.printf("%s получил от %s заказ-наряд для приготовления блюда для %s\n", cookName, dish.getWaiterName(), dish.getGuestName());

                synchronized (dish) {

                    synchronized (dishQuantity) {
                        dishQuantity++;
                        dish.setDishName("Блюдо " + dishQuantity);
                    }

                    dish.setCookName(cookName);
                    System.out.printf("%s начал готовить %s для %s по заказ-наряду от %s\n", cookName, dish.getDishName(), dish.getGuestName(), dish.getWaiterName());
                    Thread.sleep(7000);
                    System.out.printf("%s приготовил %s для %s по заказ-наряду от %s\n", cookName, dish.getDishName(), dish.getGuestName(), dish.getWaiterName());
                    synchronized (Main.readyDishes) {
                        Main.readyDishes.add(dish);
                    }
                    dish.notify();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
