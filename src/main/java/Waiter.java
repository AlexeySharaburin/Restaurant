public class Waiter {

    Restaurant restaurant;
    int i = 0;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    //Waiter
    public synchronized void waitGuest() {
        try {

            System.out.printf("%s пришёл на работу\n", Thread.currentThread().getName());
            String nameCurrentGuest;
            while (true) {

                Thread.sleep(1000);
                System.out.printf("%s готов обслужить клиента\n", Thread.currentThread().getName());
                Thread.sleep(1000);

                wait();

                synchronized (restaurant.waitingList) {
                    nameCurrentGuest = restaurant.waitingList.remove(0);
                }

                System.out.printf("%s принял заказ у %s\n", Thread.currentThread().getName(), nameCurrentGuest);
                if (Main.dishesMax != 0) {
                    i++;
                    String dishName = "Блюдо " + i;
                    System.out.printf("%s начал разогревать %s\n", Thread.currentThread().getName(), dishName);
                    Thread.sleep(7000);
                    Main.dishesMax--;
                    synchronized (restaurant.dishes) {
                        restaurant.dishes.add(new Dishes(dishName));
                        System.out.printf("%s несёт %s для %s\n", Thread.currentThread().getName(), dishName, nameCurrentGuest);
                    }

                    restaurant.dish.bringDish();
                } else {
                    System.out.println("Кухня закрыта!");
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



