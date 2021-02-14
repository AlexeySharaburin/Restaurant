public class Order {

    private String waiterName;
    private final String guestName;

    public Order(String guestName) {
        this.guestName = guestName;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getGuestName() {
        return guestName;
    }

    public boolean readyToOrder() {
        return getWaiterName() != null;
    }
}
