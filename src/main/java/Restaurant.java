public class Restaurant {

    Waiter waiter = new Waiter(this);
    Guest guest = new Guest(this);
    Cook cook = new Cook(this);

    public void comeNewCook() {
        cook.cookStart();
    }

    public void comeNewWaiter() {
        waiter.comeWaiter();
    }

    public void comeNewGuest() {
        guest.welcomeGuest();
    }
}
