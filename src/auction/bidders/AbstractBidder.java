package auction.bidders;

/**
 * A base class for a Bidder which contains the quantity and initial cash for the Auction as well
 * as basic implementations for the init and bids methods. These methods should be common among most implementations.
 * The placeBid method should not be assumed and any implementation should be forced to provide it.
 *
 * @author Michael Reid
 */
public abstract class AbstractBidder implements Bidder {

    /**
     * The quantity available for Auction
     **/
    protected int quantity;

    /**
     * The cash available to spend at Auction
     **/
    protected int cash;

    @Override
    public void init(int quantity, int cash) {
        this.quantity = quantity;
        this.cash = cash;
    }

    @Override
    public void bids(int own, int other) {
        cash -= own;
    }

    @Override
    public abstract int placeBid();

    protected int getBidOrGetZero(int bid) {
        return bid <= cash ? bid : 0;
    }

    protected int getBidOrGetAll(int bid) {
        return bid <= cash ? bid : cash;
    }
}
