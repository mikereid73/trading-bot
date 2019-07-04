package auction.bidders.types.strategy;

/**
 * Bids a predefined amount every time. When there is not enough cash it will bid what it has left.
 *
 * @author Michael Reid
 */
public class UnitStrategy extends AbstractStrategy {

    /* The amount to bid on every round if it can afford to */
    private final int bid;

    public UnitStrategy(int bid) {
        this.bid = bid;
    }


    @Override
    public int calculateBid(int cash) {
        return bid;
    }

    @Override
    public void showBids(int own, int other) {

    }
}
