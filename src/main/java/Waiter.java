public class Waiter {

    Restaurant restaurant;
    Dish dish;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    //Waiter
    public synchronized void waitGuest() {
        try {

            System.out.printf("%s пришёл на работу\n", Thread.currentThread().getName());
            String nameCurrentGuest;

            while (true) {

//                Thread.sleep(1000);
//                System.out.printf("%s готов обслужить клиента\n", Thread.currentThread().getName());
                Thread.sleep(1000);

                wait();

                synchronized (restaurant.waitingList) {
                    nameCurrentGuest = restaurant.waitingList.remove(0);
                }

                System.out.printf("%s принял заказ у %s\n", Thread.currentThread().getName(), nameCurrentGuest);

                synchronized (restaurant.dishes) {
                    if (!restaurant.dishes.isEmpty()) {

                        System.out.printf("%s несёт %s для %s\n", Thread.currentThread().getName(), restaurant.dishes.get(0).getDishName(), nameCurrentGuest);

                        dish = new Dish(Thread.currentThread().getName());

                        System.out.println(dish.getWaiterName());

                        synchronized (dish) {
                            dish.bringDish();
                        }

                    } else {
                        System.out.println("Кухня закрыта!");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Guest
    public synchronized void takeTable() {
        try {
            notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



