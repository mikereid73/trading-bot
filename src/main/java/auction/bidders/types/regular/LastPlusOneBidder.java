package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

/**
 * Always bids the opponents previous bid plus one. Small initial bid to get things started.
 * If it cannot afford a bid, it goes all in regardless.
 *
 * @author Michael Reid
 */
public class LastPlusOneBidder extends AbstractBidder {

    /**
     * The opponents last bid
     */
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
