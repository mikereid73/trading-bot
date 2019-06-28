package auction;

import auction.bidders.Bidder;
import auction.bidders.samples.RandomBidder;

public class Launcher {

    public static void main(String[] args) {
        final Bidder bidder1 = new RandomBidder();
        final Bidder bidder2 = new RandomBidder();

        Auction auction = new Auction(bidder1, bidder2, 100, 250);
        Bidder winner = auction.run();

        if(winner == bidder1) System.out.println("Bidder1 won!");
        else if(winner == bidder2) System.out.println("Bidder2 won!");
        else System.out.println("Draw!");
    }
}
