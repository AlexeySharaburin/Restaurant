public class Waiter {

    Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public void waitGuest() {
        try {

            System.out.printf("%s пришёл на работу\n", Thread.currentThread().getName());

            while (true) {

                restaurant.getFreeWaiters().add(new FreeWaiters());
                Thread.sleep(2000);
                System.out.printf("%s готов обслужить клиента\n", Thread.currentThread().getName());
                Thread.sleep(4000);

                if (restaurant.getGuests().size() == 0) {
                    System.out.println("Клиентов нет!");
                    break;
                }


                System.out.printf("%s принял заказ\n", Thread.currentThread().getName());
                restaurant.order.takeOrder();

                restaurant.getFreeWaiters().remove(0);

                System.out.printf("%s несёт блюдо\n", Thread.currentThread().getName());
                restaurant.dish.bringDish();

            }

            Thread.sleep(7000);
            System.out.printf("%s пошёл домой\n", Thread.currentThread().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
