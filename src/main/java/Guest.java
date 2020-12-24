public class Guest {

    Restaurant restaurant;

    public Guest(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // Guest
    public void waitWaiter() {
        try {

            System.out.printf("%s пришёл в ресторан\n", Thread.currentThread().getName());
            restaurant.getGuests().add(new Guests());
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

            restaurant.getGuests().remove(0);
            restaurant.getDishes().remove(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
