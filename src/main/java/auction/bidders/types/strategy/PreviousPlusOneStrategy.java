package auction.bidders.types.strategy;

/**
 * Always bids the opponents previous bid plus one. Small initial bid to get things started.
 * If it cannot afford a bid it will go all in.
 *
 * @author Michael Reid
 */
public class PreviousPlusOneStrategy extends AbstractStrategy {

    /* The amount the opponent bid in the previous round */
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
