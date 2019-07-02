package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;
import auction.bidders.types.strategy.BiddingStrategy;

import java.util.Objects;

/**
 * A smart bidder that has an interchangeable behaviour using the Strategy Pattern. A behavior can be
 * swapped in at runtime if it is decided that the current bidding strategy is not working or if the opponents
 * bidding strategy is obvious. In such cases, the preferred strategy can be injected.
 *
 * @author Michael Reid
 */
public class StrategyBidder extends AbstractBidder {

    /**
     * Bidding strategy to decide bidding amounts
     */
    private BiddingStrategy strategy;

    /**
     * Initialize with an initial strategy
     *
     * @param strategy the initial strategy
     */
    public StrategyBidder(BiddingStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    @Override
    public int placeBid() {
        // tell the strategy to calculate our bids
        int bid = strategy.calculateBid(cash);
        return getBidOrGetZero(bid);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);

        // let the strategy know about the newest bids
        strategy.showBids(own, other);
    }

    /**
     * Set the BiddingStrategy implementation to use
     *
     * @param strategy the new strategy
     */
    public void setStrategy(BiddingStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    @Override
    public String toString() {
        return "StrategyBidder {" + strategy + '}';
    }
}
