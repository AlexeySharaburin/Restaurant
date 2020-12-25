public class Dish {
    Restaurant restaurant;

    public Dish(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // Guest
    public synchronized void waitDish() {
        try {
            System.out.printf("%s ждёт своё блюдо\n", Thread.currentThread().getName());
            wait();
            restaurant.guest.haveDinner();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Waiter
    public synchronized void bringDish() {
        try {
            notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
