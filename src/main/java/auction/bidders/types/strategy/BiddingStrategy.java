package auction.bidders.types.strategy;

/**
 * Represents a bidding strategy for the auction
 *
 * @author Michael Reid
 */
public interface BiddingStrategy {

    /**
     * @param cash the available cash to work with
     * @return a bid that is within the cash range
     */
    int calculateBid(int cash);

    /**
     * Shows the bids of the two bidders.
     *
     * @param own   the bid of this bidder
     * @param other the bid of the other bidder
     */
    void showBids(int own, int other);
}
