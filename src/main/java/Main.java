import java.util.ArrayList;
import java.util.List;

public class Main {

    static int dishesMax = 3;
    static int client = 3;
    static int officiants = 2;
    static int chefs = 2;

    static final List<Dish> dishesOrders = new ArrayList<>();
    static final List<Dish> readyDishes = new ArrayList<>();
    static final List<Order> waitingList = new ArrayList<>();
    static final List<Dish> dishesForGuests = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {


        List<Thread> threadsListWaiters = new ArrayList<>();
        List<Thread> threadsListGuests = new ArrayList<>();
        List<Thread> threadsListCooks = new ArrayList<>();

        ThreadGroup waiters = new ThreadGroup("Waiters");
        ThreadGroup guests = new ThreadGroup("Guests");
        ThreadGroup cooks = new ThreadGroup("Cooks");

        System.out.println("Ресторан 'Синхронизация вручную' открыт!");
        System.out.printf("Количество блюд в меню на сегодня - %d\n", dishesMax);
        Waiter waiter = new Waiter();
        Guest guest = new Guest();
        Cook cook = new Cook();

        for (int i = 1; i < (chefs + 1); i++) {
            Thread thread = new Thread(cooks, cook::cookStart, "Повар " + i);
            thread.start();
            threadsListCooks.add(thread);
            Thread.sleep(1000);
        }


        for (int i = 1; i < (officiants + 1); i++) {
            Thread thread = new Thread(waiters, waiter::comeWaiter, "Официант " + i);
            thread.start();
            threadsListWaiters.add(thread);
            Thread.sleep(2000);
        }

        for (int i = 1; i < (client + 1); i++) {
            Thread thread = new Thread(guests, guest::welcomeGuest, "Гость " + i);
            thread.start();
            threadsListGuests.add(thread);
            Thread.sleep(7000);
        }

        for (Thread thread : threadsListGuests) {
            thread.join();
        }

        for (Thread thread : threadsListWaiters) {
            thread.interrupt();
            System.out.printf("%s закончил мыть столики и пошёл домой\n", thread.getName());
        }

        for (Thread thread : threadsListCooks) {
            thread.interrupt();
            System.out.printf("%s домыл свою плиту и пошёл домой\n", thread.getName());
        }

    }

}



