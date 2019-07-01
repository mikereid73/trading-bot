package auction.bidders.samples.strategy;

public abstract class AbstractStrategy implements BiddingStrategy {

    @Override
    public abstract int calculateBid(int cash);

    @Override
    public abstract void showBids(int own, int other);

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
