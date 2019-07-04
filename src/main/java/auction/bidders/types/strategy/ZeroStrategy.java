package auction.bidders.types.strategy;

/**
 * Always bids zero. It can return some reward if an opponent is too aggressive and runs out of money.
 * However, in practice it's mainly used for testing when you want an opponent that always loses.
 */
public class ZeroStrategy extends AbstractStrategy {

    @Override
    public int calculateBid(int cash) {
        return 0;
    }

    @Override
    public void showBids(int own, int bid2) {

    }
}
