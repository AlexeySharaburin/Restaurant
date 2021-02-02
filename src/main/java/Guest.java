public class Guest {

    Restaurant restaurant;

    public Guest(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    //  Guest
    public void waitWaiter() {
        try {
            System.out.printf("%s пришёл в ресторан\n", Thread.currentThread().getName());
            synchronized (restaurant.waitingList) {
                restaurant.waitingList.add(Thread.currentThread().getName());
            }
            restaurant.waiter.takeTable();
            Thread.sleep(5000);
            System.out.printf("%s обслуживается %s\n", Thread.currentThread().getName(), restaurant.waiter.dish.getWaiterName());

            synchronized (restaurant.waiter.dish) {
                restaurant.waiter.dish.waitDish();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
