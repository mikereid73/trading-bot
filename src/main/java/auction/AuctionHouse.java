package auction;

import auction.bidders.Bidder;
import auction.bidders.types.regular.*;
import auction.bidders.types.strategy.LastPlusOneStratgey;
import auction.bidders.types.strategy.MedianPlusOneStrategy;

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

    /**
     * the quantity available for auction
     **/
    private static final int QUANTITY = 20;

    /**
     * the cash available for auction
     **/
    private static final int CASH = 100;

    /**
     * Begin the simulation of auctions.
     * Gather dummy Users, compete them against each other, and display the results;
     */
    public void open() {
        final List<User> users = getUsers();
        for (final User user1 : users) {
            for (final User user2 : users) {
                if (user1 == user2) {
                    continue;
                }

                final Bidder bidder1 = user1.getBidder();
                final Bidder bidder2 = user2.getBidder();
                if (bidder1 == bidder2) {
                    continue;
                }

                final Auction auction = new Auction(bidder1, bidder2, QUANTITY, CASH);
                auction.addAuctionListener(this);

                final Bidder winner = auction.run();
                if (winner == bidder1) {
                    user1.recordWin();
                    user2.recordLoss();
                } else if (winner == bidder2) {
                    user2.recordWin();
                    user1.recordWin();
                } else {
                    user1.recordDraw();
                    user2.recordDraw();
                }
            }
        }

        printLeaderBoard(users);
    }

    /**
     * A hook into the Auction. Provides the per round bids of the current Auction.
     *
     * @param bid1 the bid made by bidder1
     * @param bid2 the bid made by bidder2
     */
    @Override
    public void onBidsRevealed(int bid1, int bid2) {
        // System.out.printf("Bidder1 bid %d, Bidder2 bid %d\n", bid1, bid2);
    }

    /**
     * Returns a list of Users for the Auction
     *
     * @return a dummy set of Users
     */
    private List<User> getUsers() {
        final List<User> users = new ArrayList<>();
        users.add(new User("Mike", new StrategyBidder(new MedianPlusOneStrategy())));
        users.add(new User("Christie", new AveragePlusOneBidder()));
        users.add(new User("Niki", new ZeroBidder()));
        users.add(new User("Mark", new MedianPlusOneBidder()));
        users.add(new User("Miley", new RandomBidder()));
        users.add(new User("Padraic", new LastPlusOneBidder()));
        return users;
    }

    private List<User> getStrategyUsers() {
        final List<User> users = new ArrayList<>();
        users.add(new User("Mike", new StrategyBidder(new LastPlusOneStratgey())));
        users.add(new User("Christie", new AveragePlusOneBidder()));
        users.add(new User("Niki", new ZeroBidder()));
        users.add(new User("Mark", new MedianPlusOneBidder()));
        users.add(new User("Miley", new RandomBidder()));
        users.add(new User("Padraic", new LastPlusOneBidder()));
        return users;
    }

    /**
     * Print out the results of the Auction
     *
     * @param users the list of Users who took part
     */
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
}
