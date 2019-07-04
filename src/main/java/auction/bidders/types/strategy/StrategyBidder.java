package auction.bidders.types.strategy;

import auction.bidders.AbstractBidder;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A smart bidder that has an interchangeable behaviour using the Strategy Pattern. A behavior can be
 * swapped in at runtime if it is decided that the current bidding strategy is not working or if the opponents
 * bidding strategy is obvious. In such cases, the preferred strategy can be injected.
 *
 * @author Michael Reid
 */
public class StrategyBidder extends AbstractBidder {

    private final BiddingStrategy defaultStrategy;
    private BiddingStrategy currentStrategy;

    private int opponentsCash;

    public StrategyBidder(BiddingStrategy defaultStrategy) {
        this.defaultStrategy = Objects.requireNonNull(defaultStrategy);
    }

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);

        opponentsCash = cash;
        currentStrategy = defaultStrategy;
    }

    @Override
    public int placeBid() {
        // tell the strategy to calculate our bids
        int bid = currentStrategy.calculateBid(cash);
        return getBidOrGetZero(bid);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);

        opponentsCash -= other;

        // let the strategy know about the newest bids
        currentStrategy.showBids(own, other);
        checkAndChangeStrategy();
    }

    /**
     * A VERY BASIC concept for adapting the bidding logic.
     */
    private void checkAndChangeStrategy() {
        if (opponentsCash == 0 && !(currentStrategy instanceof UnitStrategy)) {
            // they have no cash left, just bid 1 to win
            currentStrategy = new UnitStrategy(1);
        }
    }

    @Override
    public String toString() {
        return "StrategyBidder {" + currentStrategy + '}';
    }
}
