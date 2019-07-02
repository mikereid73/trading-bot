package auction.bidders.types.strategy;

/**
 * Always bids the opponents previous bid plus one. Small initial bid to get things started.
 *
 * @author Michael Reid
 */
public class LastPlusOneStratgey extends AbstractStrategy {

    private int lastBid;

    @Override
    public int calculateBid(int cash) {
        return lastBid + 1;
    }

    @Override
    public void showBids(int own, int other) {
        lastBid = other;
    }
}
