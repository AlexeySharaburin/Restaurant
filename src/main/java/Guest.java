public class Guest {

    Restaurant restaurant;
    Dinner dinner;

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
            Dish currentDish = restaurant.currentDish;
            System.out.println(currentDish.getGuestName());


            synchronized (currentDish) {
                dinner.waitDish();
            }


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
