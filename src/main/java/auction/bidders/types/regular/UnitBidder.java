package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

/**
 * Bids a predefined amount every time. When there is not enough cash it will bid what it has left.
 *
 * @author Michael Reid
 */
public class UnitBidder extends AbstractBidder {

    /* The amount to bid on every round if it can afford to */
    private final int bid;

    public UnitBidder(int bid) {
        this.bid = bid;
    }

    @Override
    public int placeBid() {
        return getBidOrGetAll(bid);
    }
}
