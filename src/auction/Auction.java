package auction;

import auction.bidders.Bidder;

import java.util.Objects;

public class Auction {

    private final Bidder bidder1;
    private final Bidder bidder2;
    private final int quantity;
    private final int cash;

    private AuctionListener auctionListener;

    public Auction(Bidder bidder1, Bidder bidder2, int quantity, int cash) {
        this.bidder1 = Objects.requireNonNull(bidder1);
        this.bidder2 = Objects.requireNonNull(bidder2);
        this.quantity = validateQuantity(quantity);
        this.cash = validateCash(cash);
    }

    public Bidder run() {
        bidder1.init(quantity, cash);
        bidder2.init(quantity, cash);

        final BidderScoreCard bidder1ScoreCard = new BidderScoreCard(quantity, cash);
        final BidderScoreCard bidder2ScoreCard = new BidderScoreCard(quantity, cash);

        for (int round = 1; round <= quantity / 2; round++) {

            // get bidders next bids
            final int bid1 = bidder1.placeBid();
            final int bid2 = bidder2.placeBid();

            // reveal bids to each other
            bidder1.bids(bid1, bid2);
            bidder2.bids(bid2, bid1);
            if (auctionListener != null) {
                auctionListener.onBidsRevealed(bid1, bid2);
            }

            bidder1ScoreCard.cash -= bid1;
            bidder2ScoreCard.cash -= bid2;

            // decide winner
            if (bid1 > bid2) {
                bidder1ScoreCard.quantity += 2;
            } else if (bid2 > bid1) {
                bidder2ScoreCard.quantity += 2;
            } else {
                bidder1ScoreCard.quantity++;
                bidder2ScoreCard.quantity++;
            }
        }

        if (bidder1ScoreCard.compareTo(bidder2ScoreCard) > 0) {
            return bidder1;
        } else if (bidder1ScoreCard.compareTo(bidder2ScoreCard) < 0) {
            return bidder2;
        } else {
            return null;
        }
    }

    private int validateQuantity(int quantity) {
        if (quantity % 2 != 0) {
            throw new IllegalArgumentException("quantity must be even");
        }
        return quantity;
    }

    private int validateCash(int cash) {
        if (cash < 0) {
            throw new IllegalArgumentException("cash cannot be less than zero");
        }
        return cash;
    }

    public void addAuctionListener(AuctionListener auctionListener) {
        this.auctionListener = Objects.requireNonNull(auctionListener);
    }

    private static class BidderScoreCard implements Comparable<BidderScoreCard> {

        private int quantity;
        private int cash;

        public BidderScoreCard(int quantity,  int cash) {
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