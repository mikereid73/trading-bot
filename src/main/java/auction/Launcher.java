package auction;

/**
 * The entry point of the application.
 *
 * @author Michael Reid
 */
public class Launcher {

    public static void main(String[] args) {
        AuctionHouse auctionHouse = new AuctionHouse();
        auctionHouse.open();
    }
}
