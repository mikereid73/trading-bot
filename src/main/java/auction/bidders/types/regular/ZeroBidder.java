package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

/**
 * Always bids zero
 */
public class ZeroBidder extends AbstractBidder {

    @Override
    public int placeBid() {
        return 0;
    }
}
