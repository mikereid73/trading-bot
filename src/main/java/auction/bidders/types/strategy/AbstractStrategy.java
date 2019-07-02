package auction.bidders.types.strategy;

/**
 * An abstract bidding strategy base class. Currently only used for defining a custom toString()
 * Common strategy values, objects, and behaviours should be stored here.
 *
 * @author Michael Reid
 */
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
