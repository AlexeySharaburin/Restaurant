public class Dinner {

    Restaurant restaurant;

    public Dinner(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // Guest
    public synchronized void waitDish() {
        try {
            wait();
            synchronized (restaurant.dishes) {
                System.out.printf("%s получил %s\n", Thread.currentThread().getName(), restaurant.dishes.remove(0).getDishName());
            }
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
