public class Main {

    static int dishesMax = 5;
    static int client = 5;
    static int officiant = 3;

    public static void main(String[] args) throws InterruptedException {

        final Restaurant restaurant = new Restaurant();

        new Thread(null, restaurant::cookCooking, "Повар").start();

        for (int i = 1; i < (officiant + 1); i++) {
            new Thread(null, restaurant::startWorking, "Официант " + i).start();
            Thread.sleep(2000);
        }

        for (int i = 1; i < (client + 1); i++) {
            new Thread(null, restaurant::welcomeGuest, "Гость " + i).start();
            Thread.sleep(7000);
        }
    }
}


