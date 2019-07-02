package auction.bidders.types.strategy;

public class ZeroStrategy extends AbstractStrategy {

    @Override
    public int calculateBid(int cash) {
        return 0;
    }

    @Override
    public void showBids(int own, int bid2) {

    }
}
