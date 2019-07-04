package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

/**
 * Always bids the opponents previous bid plus one. Small initial bid to get things started.
 * If it cannot afford a bid it will go all in.
 *
 * @author Michael Reid
 */
public class PreviousPlusOneBidder extends AbstractBidder {

    /* The amount the opponent bid in the previous round */
    private int opponentsPreviousBid;

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
        opponentsPreviousBid = 0;
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);
        opponentsPreviousBid = other;
    }

    @Override
    public int placeBid() {
        int bid = opponentsPreviousBid + 1;
        return getBidOrGetAll(bid);
    }
}
