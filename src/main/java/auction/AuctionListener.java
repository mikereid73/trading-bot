package auction;

/**
 * A simple callback interface to allow for a hook into the bidding process
 *
 * @author Michael Reid
 */
public interface AuctionListener {
    /**
     * Reports back with the values of the two bids made by the Bidders
     *
     * @param bid1 the bid made by bidder1
     * @param bid2 the bid made by bidder2
     */
    void onBidsRevealed(int bid1, int bid2);
}
