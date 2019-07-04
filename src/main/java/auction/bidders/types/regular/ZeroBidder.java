package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

/**
 * Always bids zero. It can return some reward if an opponent is too aggressive and runs out of money.
 * However, in practice it's mainly used for testing when you want an opponent that always loses.
 */
public class ZeroBidder extends AbstractBidder {

    @Override
    public int placeBid() {
        return 0;
    }
}
