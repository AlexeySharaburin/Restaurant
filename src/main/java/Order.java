public class Order {
    Restaurant restaurant;

    public Order(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // Guest
    public synchronized void makeOrder() {
        try {
            System.out.printf("%s ждёт официанта\n", Thread.currentThread().getName());
            wait();
            restaurant.dish.waitDish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Waiter
    public synchronized void takeOrder() {
        try {
            notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


