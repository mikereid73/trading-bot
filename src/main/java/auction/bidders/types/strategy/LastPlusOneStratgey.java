package auction.bidders.types.strategy;

/**
 * Always bids the opponents previous bid plus one. Small initial bid to get things started.
 *
 * @author Michael Reid
 */
public class LastPlusOneStratgey extends AbstractStrategy {

    private int opponentsPreviousBid;

    @Override
    public int calculateBid(int cash) {
        return opponentsPreviousBid + 1;
    }

    @Override
    public void showBids(int own, int other) {
        opponentsPreviousBid = other;
    }
}
