package auction;

import auction.bidders.Bidder;
import auction.bidders.samples.RandomBidder;
import auction.wrapper.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class AuctionHouse implements AuctionListener {

    private static final int QUANTITY = 20;
    private static final int CASH = 100;

    public void open() {
         List<User> players = getPlayers();

        for (User firstPlayer : players) {
            for (User secondPlayer : players) {
                 Bidder bidder1 = firstPlayer.getBidder();
                 Bidder bidder2 = secondPlayer.getBidder();

                if (bidder1 == bidder2) {
                    continue;
                }

                Auction auction = new Auction(bidder1, bidder2, QUANTITY, CASH);
                auction.addAuctionListener(this);
                Bidder winner = auction.run();

                if (winner == bidder1) {
                    firstPlayer.recordWin();
                    secondPlayer.recordLoss();
                } else if (winner == bidder2) {
                    secondPlayer.recordWin();
                    firstPlayer.recordWin();
                } else {
                    firstPlayer.recordDraw();
                    secondPlayer.recordDraw();
                }
            }
        }

        printLeaderBoard(players);
    }

    @Override
    public void onBidsRevealed(int bid1, int bid2) {
        // System.out.printf("Bidder1 bid %d, Bidder2 bid %d\n", bid1, bid2);
    }

    private List<User> getPlayers() {
        List<User> players = new ArrayList<>();
        players.add(new User("Mike", new RandomBidder()));
        players.add(new User("Christie", new RandomBidder()));
        players.add(new User("Niki", new RandomBidder()));
        players.add(new User("Mark", new RandomBidder()));
        players.add(new User("Miley", new RandomBidder()));
        players.add(new User("Padraic", new RandomBidder()));
        players.add(new User("Joe", new RandomBidder()));
        players.add(new User("McDuff", new RandomBidder()));
        players.add(new User("Dan", new RandomBidder()));
        return players;
    }

    private void printLeaderBoard(List<User> players) {
        System.out.println();
        System.out.println("*************************************");
        System.out.println("******* L E A D E R B O A R D *******");
        System.out.println("*************************************");
        players.sort(Collections.reverseOrder());
        ListIterator<User> iterator = players.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            User player = iterator.next();
            System.out.println(
                    (index + 1) + ". "
                            + player.getName()
                            + " has " + player.getWins() + " wins,"
                            + player.getLosses() + " losses, and "
                            + player.getDraws() + " draws from "
                            + player.getAuctions() + " auctions. "
                            + "Longest winning streak: " + player.getLongestWinningStreak()
            );
        }
    }
}
