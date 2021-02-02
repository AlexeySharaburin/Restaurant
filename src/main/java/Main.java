import java.util.ArrayList;
import java.util.List;

public class Main {

    static int dishesMax = 5;
    static int client = 5;
    static int officiants = 3;

    public static void main(String[] args) throws InterruptedException {

        final Restaurant restaurant = new Restaurant();

        List<Thread> threadsListWaiters = new ArrayList<>();
        List<Thread> threadsListGuests = new ArrayList<>();


        ThreadGroup waiters = new ThreadGroup("Waiters");
        ThreadGroup clients = new ThreadGroup("Clients");

        System.out.println("Ресторан открыт!");
        restaurant.cooking();

        for (int i = 1; i < (officiants + 1); i++) {
            Thread thread = new Thread(waiters, restaurant::startWorking, "Официант " + i);
            thread.start();
            threadsListWaiters.add(thread);
            Thread.sleep(2000);
        }

        for (int i = 1; i < (client + 1); i++) {
            Thread thread = new Thread(clients, restaurant::welcomeGuest, "Гость " + i);
            thread.start();
            threadsListGuests.add(thread);
            Thread.sleep(7000);
        }

        for (Thread thread : threadsListGuests) {
            thread.join();
        }

        for (Thread thread : threadsListWaiters) {
            thread.interrupt();
            System.out.printf("%s пошёл домой\n", thread.getName());
        }

    }

}



