package auction;

import auction.bidders.Bidder;

import java.util.Objects;

public class Auction {

    private final Bidder bidder1;
    private final Bidder bidder2;
    private final int quantity;
    private final int cash;

    public Auction(Bidder bidder1, Bidder bidder2, int quantity, int cash) {
        this.bidder1 = Objects.requireNonNull(bidder1);
        this.bidder2 = Objects.requireNonNull(bidder2);
        this.quantity = quantity;
        this.cash = cash;
    }

    public Bidder run() {
        bidder1.init(quantity, cash);
        bidder2.init(quantity, cash);

        // temporary score tracker
        int bidder1Score = 0;
        int bidder2Score = 0;
        int bidder1RemainingCash = cash;
        int bidder2RemainingCash = cash;

        for(int round = 1; round <= quantity / 2; round++) {

            // get bidders next bids
            final int bid1 = bidder1.placeBid();
            final int bid2 = bidder2.placeBid();

            // reveal bids to each other
            bidder1.bids(bid1, bid2);
            bidder2.bids(bid2, bid1);

            bidder1RemainingCash -= bid1;
            bidder2RemainingCash -= bid2;

            if(bid1 > bid2) {
                bidder1Score += 2;
            } else if(bid2 > bid1) {
                bidder2Score += 2;
            } else {
                bidder1Score++;
                bidder2Score++;
            }
        }

        // decide who was the overall winner
        if(bidder1Score > bidder2Score) {
            return bidder1;
        } else if(bidder2Score > bidder1Score) {
            return bidder2;
        } else {
            // both bought the same quantity. User remaining cash to decide
            if(bidder1RemainingCash > bidder1RemainingCash) {
                return bidder1;
            } else if(bidder2RemainingCash > bidder2RemainingCash) {
                return bidder2;
            }else {
                return null;
            }
        }
    }
}
