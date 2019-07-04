package auction.bidders.types.strategy;

public class UnitStrategy extends AbstractStrategy {

    private final int bid;

    public UnitStrategy(int bid) {
        this.bid = bid;
    }


    @Override
    public int calculateBid(int cash) {
        return bid;
    }

    @Override
    public void showBids(int own, int other) {

    }
}
