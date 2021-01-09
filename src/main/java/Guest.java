public class Guest {

    Restaurant restaurant;

    public Guest(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // Guest
    public void waitWaiter() {
        try {
            System.out.printf("%s пришёл в ресторан\n", Thread.currentThread().getName());
            synchronized (restaurant.waitingList) {
                restaurant.waitingList.add(Thread.currentThread().getName());
                System.out.println("Лист ожидания: " + restaurant.waitingList);
            }
            restaurant.waiter.takeTable();
            restaurant.order.makeOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Guest
    public void haveDinner() {
        try {
            System.out.printf("%s приступил к ужину\n", Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.printf("%s закончил ужинать и пошёл домой\n", Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
