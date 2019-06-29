package auction;

/**
 * The entry point of the application.
 */
public class Launcher {

    public static void main(String[] args) {
        final AuctionHouse auctionHouse = new AuctionHouse();
        auctionHouse.open();
    }
}
