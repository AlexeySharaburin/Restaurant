import java.util.*;

public class Restaurant {

    final List<Dishes> dishes = new ArrayList<>();
    final List<String> waitingList = new ArrayList<>();

    Waiter waiter = new Waiter(this);
    Guest guest = new Guest(this);
    Dish dish = new Dish(this);

    public void startWorking() {
        waiter.waitGuest();
    }

    public void welcomeGuest() {
        guest.waitWaiter();
    }


}


