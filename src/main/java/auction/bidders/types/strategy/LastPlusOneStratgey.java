package auction.bidders.types.strategy;

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
