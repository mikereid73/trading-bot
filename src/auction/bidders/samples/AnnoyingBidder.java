package auction.bidders.samples;

import auction.bidders.AbstractBidder;

public class AnnoyingBidder extends AbstractBidder {

    private int lastBid;

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
        lastBid = 0;
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);
        lastBid = other;
    }

    @Override
    public int placeBid() {
        int bid = lastBid + 1;
        return getBidOrGetAll(bid);
    }
}
