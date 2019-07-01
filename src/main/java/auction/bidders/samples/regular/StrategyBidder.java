package auction.bidders.samples.regular;

import auction.bidders.AbstractBidder;
import auction.bidders.samples.strategy.BiddingStrategy;

import java.util.Objects;

public class StrategyBidder extends AbstractBidder {

    private BiddingStrategy strategy;

    public StrategyBidder(BiddingStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    @Override
    public int placeBid() {
        int bid = strategy.calculateBid(cash);
        return getBidOrGetZero(bid);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);

        strategy.showBids(own, other);
    }

    public void setStrategy(BiddingStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    @Override
    public String toString() {
        return "StrategyBidder {" + strategy.getClass().getSimpleName() + '}';
    }
}
