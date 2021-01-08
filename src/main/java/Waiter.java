public class Waiter {

    Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    //Waiter
    public synchronized void waitGuest() {
        try {

            System.out.printf("%s пришёл на работу\n", Thread.currentThread().getName());
            String nameCurrentGuest;
            while (true) {

                Thread.sleep(2000);
                System.out.printf("%s готов обслужить клиента\n", Thread.currentThread().getName());
                Thread.sleep(4000);

                wait();

                synchronized (restaurant.waitingList) {
                    nameCurrentGuest = restaurant.waitingList.remove(0);
                }

                restaurant.order.takeOrder();

                System.out.printf("%s принял заказ у %s\n", Thread.currentThread().getName(), nameCurrentGuest);

                System.out.printf("%s несёт блюдо %s\n", Thread.currentThread().getName(), nameCurrentGuest);

                restaurant.dish.bringDish();

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



