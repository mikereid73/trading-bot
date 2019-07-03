package auction;

import auction.bidders.Bidder;

import java.util.Objects;

/**
 * Auction class to simulate an Auction between two Bidder implementations
 *
 * @author Michael Reid
 */
public class Auction {

    /**
     * The first bidder in the Auction
     **/
    private final Bidder bidder1;

    /**
     * The second bidder in the Auction
     **/
    private final Bidder bidder2;

    /**
     * The quantity available to buy in the Auction
     **/
    private final int quantity;

    /**
     * The cash available to spend in the Auction
     **/
    private final int cash;

    /**
     * The callback interface to allow a hook into the Auction bidding rounds
     **/
    private AuctionListener auctionListener;

    /**
     * Create a new Auction with two Bidders, a quantity to auction, and cash to spend.
     *
     * @param bidder1  the first bidder
     * @param bidder2  the second bidder
     * @param quantity the starting quantity
     * @param cash     the starting cash
     */
    public Auction(Bidder bidder1, Bidder bidder2, int quantity, int cash) {
        this.bidder1 = Objects.requireNonNull(bidder1);
        this.bidder2 = Objects.requireNonNull(bidder2);
        this.quantity = validateQuantity(quantity);
        this.cash = validateCash(cash);
    }

    /**
     * Run the Auction on a round by round basis. Returns the winning Bidder at the end. Returns null if
     * there is no winner at the end.
     *
     * @return the winning Bidder
     */
    public Bidder run() {
        // initialize the bidders with quantity and cash
        bidder1.init(quantity, cash);
        bidder2.init(quantity, cash);

        // create the bidders scorecards
        final BidderScoreCard bidder1ScoreCard = new BidderScoreCard(quantity, cash);
        final BidderScoreCard bidder2ScoreCard = new BidderScoreCard(quantity, cash);

        // run the auction round by round
        for (int round = 1; round <= quantity / 2; round++) {

            // get bidders next bids
            int bid1 = bidder1.placeBid();
            int bid2 = bidder2.placeBid();

            // check if a bidder can afford it (and not cheating) otherwise they forfeit and pay 0
            if (bid1 > bidder1ScoreCard.cash) {
                bid1 = 0;
            }
            if (bid2 > bidder2ScoreCard.cash) {
                bid2 = 0;
            }

            // reveal bids to each other
            bidder1.bids(bid1, bid2);
            bidder2.bids(bid2, bid1);

            // report back to the listener (if there is one) with this rounds bids
            if (auctionListener != null) {
                auctionListener.onBidsRevealed(bid1, bid2);
            }

            // make the bidders pay
            bidder1ScoreCard.cash -= bid1;
            bidder2ScoreCard.cash -= bid2;

            // decide round winner
            if (bid1 > bid2) {
                bidder1ScoreCard.quantity += 2;
            } else if (bid2 > bid1) {
                bidder2ScoreCard.quantity += 2;
            } else {
                bidder1ScoreCard.quantity++;
                bidder2ScoreCard.quantity++;
            }
        }

        // decide overall winner
        if (bidder1ScoreCard.compareTo(bidder2ScoreCard) > 0) {
            return bidder1;
        } else if (bidder1ScoreCard.compareTo(bidder2ScoreCard) < 0) {
            return bidder2;
        } else {
            return null;
        }
    }

    /**
     * Validate if the quantity is allowed in the Auction
     *
     * @param quantity the supplied quantity
     * @return the validated quantity
     */
    private int validateQuantity(int quantity) {
        if (quantity % 2 != 0) {
            throw new IllegalArgumentException("quantity must be even");
        }
        return quantity;
    }

    /**
     * Validate if the cash is allowed in the Auction
     *
     * @param cash the supplied cash
     * @return the validated cash
     */
    private int validateCash(int cash) {
        if (cash < 0) {
            throw new IllegalArgumentException("cash cannot be less than zero");
        }
        return cash;
    }

    /**
     * Set the callback listener
     *
     * @param auctionListener the listener implementation
     */
    public void addAuctionListener(AuctionListener auctionListener) {
        this.auctionListener = Objects.requireNonNull(auctionListener);
    }

    /**
     * Scorecard class
     *
     * @author Michael Reid
     */
    private static class BidderScoreCard implements Comparable<BidderScoreCard> {

        /* The quantity successfully purchased at auction */
        private int quantity;

        /* The remaining cash for the auction */
        private int cash;

        public BidderScoreCard(int quantity, int cash) {
            this.quantity = quantity;
            this.cash = cash;
        }

        @Override
        public int compareTo(BidderScoreCard other) {
            if (other == null) return 1;
            if (other == this) return 0;
            if (quantity > other.quantity) return 1;
            if (quantity < other.quantity) return -1;
            return Integer.compare(cash, other.cash);
        }
    }
}