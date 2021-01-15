import java.util.*;

public class Restaurant {

    final List<Dishes> dishes = new ArrayList<>();
    final List<String> waitingList = new ArrayList<>();

    Waiter waiter = new Waiter(this);
    Guest guest = new Guest(this);
    final Dish dish = new Dish(this);

    public void startWorking() {
        waiter.waitGuest();
    }

    public void welcomeGuest() {
        guest.waitWaiter();
    }

    public void cooking() {
        int i = 0;

        while (Main.dishesMax > i) {
            i++;
            String dishName = "Блюдо " + i;
            dishes.add(new Dishes(dishName));
        }
        System.out.println("Список блюд на сегодня: ");
        for ( Dishes dishes : dishes) {
            System.out.println(dishes.getDishName());
        }
    }


}


