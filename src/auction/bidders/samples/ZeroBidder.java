package auction.bidders.samples;

import auction.bidders.AbstractBidder;

public class ZeroBidder extends AbstractBidder {

    @Override
    public int placeBid() {
        return 0;
    }
}
