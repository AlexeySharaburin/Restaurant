public class Dish {

    String waiterName;
    Restaurant restaurant;

    public Dish(String waiterName) {
        this.waiterName = waiterName;
    }


    // Guest
    public synchronized void waitDish() {
        try {
            wait();
            synchronized (restaurant.dishes) {
                System.out.printf("%s получил %s\n", Thread.currentThread().getName(), restaurant.dishes.remove(0).getDishName());
            }
            System.out.printf("%s приступил к ужину\n", Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.printf("%s закончил ужинать и пошёл домой\n", Thread.currentThread().getName());
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

    public String getWaiterName() {
        return waiterName;
    }
}
