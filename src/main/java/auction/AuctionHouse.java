package auction;

import auction.bidders.Bidder;
import auction.bidders.types.regular.*;
import auction.bidders.types.strategy.MedianPlusOneStrategy;
import auction.bidders.types.strategy.StrategyBidder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * The AuctionHouse is used to simulate n number of auctions. It generates dummy data of players and
 * their bidders and pits them against each other. At the end it prints a brief summary in the form
 * of a LeaderBoard.
 *
 * @author Michael Reid
 */
public class AuctionHouse implements AuctionListener {

    /* The quantity available for auction */
    private final int quantity;

    /* The cash available for auction */
    private final int cash;

    /* The number of times to repeat the auction simulations */
    private final int numberOfSimulations;

    public AuctionHouse(int quantity, int cash, int numberOfSimulations) {
        this.quantity = quantity;
        this.cash = cash;
        this.numberOfSimulations = numberOfSimulations;
    }

    /* Begin simulating auctions between users and their bidders */
    public void startAuctionSimulations() {
        final List<User> users = getTestUsers();

        System.out.println("Starting simulation...");
        for (int i = 0; i < numberOfSimulations; i++) {
        System.out.format("Simulation number %d of %d\n", i + 1, numberOfSimulations);
            performSimulation(users);
        }

        printLeaderBoard(users);
    }

    private void performSimulation(List<User> users) {
        for (final User user1 : users) {
            for (final User user2 : users) {
                if (user1 == user2) {
                    // the same user instance shouldn't be able to play against itself
                    continue;
                }

                final Bidder bidder1 = user1.getBidder();
                final Bidder bidder2 = user2.getBidder();
                if (bidder1 == bidder2) {
                    // the same bidder instance shouldn't be able to play against itself
                    continue;
                }

                final Auction auction = new Auction(bidder1, bidder2, quantity, cash);
                // hook into the action so we can spy on round by round bids
                auction.addAuctionListener(this);

                final Bidder winner = auction.run();
                if (winner == bidder1) {
                    user1.recordWin();
                    user2.recordLoss();
                } else if (winner == bidder2) {
                    user2.recordWin();
                    user1.recordLoss();
                } else {
                    user1.recordDraw();
                    user2.recordDraw();
                }
            }
        }
    }

    private List<User> getTestUsers() {
        final List<User> users = new ArrayList<>();
        users.add(new User("Mike", new StrategyBidder(new MedianPlusOneStrategy())));
        users.add(new User("Christie", new AveragePlusOneBidder()));
        //users.add(new User("Niki", new ZeroBidder()));
        users.add(new User("Mark", new MedianPlusOneBidder()));
        users.add(new User("Miley", new RandomBidder()));
        users.add(new User("Padraic", new LastPlusOneBidder()));
        users.add(new User("Dan", new UnitBidder(3)));
        return users;
    }

    private void printLeaderBoard(List<User> users) {
        System.out.println();
        System.out.println("*************************************");
        System.out.println("******* L E A D E R B O A R D *******");
        System.out.println("*************************************");
        users.sort(Collections.reverseOrder());
        ListIterator<User> iterator = users.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            User current = iterator.next();
            System.out.println(
                    (index + 1) + ". " + current
            );
        }
    }

    /*
     * A hook into the Auction. Provides the per round bids of the current Auction.
     */
    @Override
    public void onBidsRevealed(int bid1, int bid2) {
        // System.out.format("Bidder 1 made a bid of: %d and Bidder 2 made a bid of %d\n",bid1, bid2);
    }
}
