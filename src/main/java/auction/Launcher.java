package auction;

/**
 * The entry point of the application.
 *
 * @author Michael Reid
 */
public class Launcher {

    public static void main(String[] args) {
        // initialize the a starting quantity of 50, cash of 1000, and perform 100 simulation runs
        AuctionHouse auctionHouse = new AuctionHouse(50, 1000, 100);
        auctionHouse.startAuctionSimulations();
    }
}
