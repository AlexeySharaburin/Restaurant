import java.util.*;

public class Restaurant {

    List<Dishes> dishes = new ArrayList<>();
    final List<String> waitingList = new ArrayList<>();

    Waiter waiter = new Waiter(this);
    Guest guest = new Guest(this);
    Order order = new Order(this);
    Dish dish = new Dish(this);

    public void cookCooking() {
        try {
            int i = 0;
            System.out.printf("%s открыл 'РесторанЪ'!\n", Thread.currentThread().getName());
            while (Main.dishesMax != 0) {
                i++;
                String dishName = "Блюдо " + i;
                System.out.printf("%s начал готовить %s\n", Thread.currentThread().getName(), dishName);
                Thread.sleep(5000);
                Main.dishesMax--;

                synchronized (dishes) {
                    dishes.add(new Dishes(dishName));
                    System.out.printf("%s приготовил %s\n", Thread.currentThread().getName(), dishName);
                }


            }
            Thread.sleep(5000);
            System.out.printf("%s закончил работу и пошёл домой\n", Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startWorking() {
        waiter.waitGuest();
    }

    public void welcomeGuest() {
        guest.waitWaiter();
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

}
