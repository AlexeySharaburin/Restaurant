import java.util.*;

public class Restaurant {

//    List<FreeWaiters> freeWaiters = new ArrayList<>();
//    List<Guests> guests = new ArrayList<>();
    List<Dishes> dishes = new ArrayList<>();
    List<String> waitingList = new ArrayList<>();

    Waiter waiter = new Waiter(this);
    Guest guest = new Guest(this);

    Order order = new Order(this);
    Dish dish = new Dish(this);

    public void cookCooking() {
        try {
            System.out.printf("%s открыл 'РесторанЪ'!\n", Thread.currentThread().getName());
            while (Main.dishesMax != 0) {
                System.out.printf("%s начал готовить блюдо\n", Thread.currentThread().getName());
                Thread.sleep(3000);
                Main.dishesMax--;
                getDishes().add(new Dishes());
                System.out.printf("%s приготовил блюдо\n", Thread.currentThread().getName());
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

//    public List<FreeWaiters> getFreeWaiters() {
//        return freeWaiters;
//    }
//
//    public List<Guests> getGuests() {
//        return guests;
//    }


}
