package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

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
