public class Guest {

    private Integer dishQuantity = 0;

    public void welcomeGuest() {
        String guestName = Thread.currentThread().getName();

        try {
            System.out.printf("%s пришёл в ресторан\n", guestName);
            Thread.sleep(2000);


            synchronized (dishQuantity) {
                dishQuantity++;
                if (dishQuantity > Main.dishesMax) {
                    Thread.currentThread().interrupt();
                }
            }

            Order order = new Order(guestName);
            Dish dish;


            synchronized (Main.waitingList) {
                System.out.printf("%s готов сделать заказ\n", guestName);
                Main.waitingList.add(order);
                Main.waitingList.notify();
            }

            synchronized (order) {
                if (!order.readyToOrder()) {
                    order.wait();
                }
                System.out.printf("%s сделал заказ у %s\n", guestName, order.getWaiterName());
                order.notify();
                order.wait();

                synchronized (Main.dishesForGuests) {
                    dish = Main.dishesForGuests.remove(0);
                }

                synchronized (dish) {
                    System.out.printf("%s принёс %s для %s с надписью 'Дорогому '%s' от %s'\n", order.getWaiterName(), dish.getDishName(), guestName, dish.getGuestName(), dish.getCookName());
                    System.out.printf("%s начал ужинать\n", guestName);
                    Thread.sleep(5000);
                    System.out.printf("%s съел всё своё %s, оставил на чай %s, поблагодарил %s и пошёл домой\n", guestName, dish.getDishName(), dish.getWaiterName(), dish.getCookName());
                }
                order.notify();
            }

        } catch (Exception e) {
            System.out.printf("Так как на кухне закончилсь продукты, %s пошёл в соседний ресторан 'Коллекции для параллельной работы'\n", guestName);
        }

    }
}
