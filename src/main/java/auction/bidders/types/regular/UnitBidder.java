package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

public class UnitBidder extends AbstractBidder {

    private final int bid;

    public UnitBidder(int bid) {
        this.bid = bid;
    }

    @Override
    public int placeBid() {
        return getBidOrGetAll(bid);
    }
}
