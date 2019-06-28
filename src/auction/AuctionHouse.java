package auction;

import auction.bidders.Bidder;
import auction.bidders.samples.RandomBidder;
import auction.wrapper.BidderWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class AuctionHouse implements AuctionListener {

    public void open() {
        final List<BidderWrapper> players = getPlayers();

        for (BidderWrapper firstPlayer : players) {
            String name = firstPlayer.getName();
            System.out.println(name.toUpperCase());
            for (BidderWrapper secondPlayer : players) {
                final Bidder bidder1 = firstPlayer.getBidder();
                final Bidder bidder2 = secondPlayer.getBidder();

                if (bidder1 == bidder2) {
                    continue;
                }

                String name2 = secondPlayer.getName();
                System.out.println(name + " vs " + name2);

                Auction auction = new Auction(bidder1, bidder2, 100, 250);
                auction.addAuctionListener(this);
                Bidder winner = auction.run();

                if (winner == bidder1) {
                    System.out.println(name + " won!");
                    firstPlayer.recordWin();
                    secondPlayer.recordLoss();
                } else if (winner == bidder2) {
                    System.out.println(name2 + " won!");
                    secondPlayer.recordWin();
                    firstPlayer.recordWin();
                } else {
                    System.out.println("Draw!");
                    firstPlayer.recordDraw();
                    secondPlayer.recordDraw();
                }
                System.out.println();
            }
            System.out.println();
        }

        printLeaderBoard(players);
    }

    @Override
    public void onBidsRevealed(int bid1, int bid2) {
        System.out.printf("Bidder1 bid %d, Bidder2 bid %d\n", bid1, bid2);
    }

    private List<BidderWrapper> getPlayers() {
        List<BidderWrapper> players = new ArrayList<>();
        players.add(new BidderWrapper("Mike", new RandomBidder()));
        players.add(new BidderWrapper("Christie", new RandomBidder()));
        players.add(new BidderWrapper("Niki", new RandomBidder()));
        players.add(new BidderWrapper("Mark", new RandomBidder()));
        players.add(new BidderWrapper("Miley", new RandomBidder()));
        players.add(new BidderWrapper("Padraic", new RandomBidder()));
        players.add(new BidderWrapper("Joe", new RandomBidder()));
        players.add(new BidderWrapper("McDuff", new RandomBidder()));
        players.add(new BidderWrapper("Dan", new RandomBidder()));
        return players;
    }

    private void printLeaderBoard(List<BidderWrapper> players) {
        Collections.sort(players, Collections.reverseOrder());
        ListIterator<BidderWrapper> iterator = players.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            BidderWrapper player = iterator.next();
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