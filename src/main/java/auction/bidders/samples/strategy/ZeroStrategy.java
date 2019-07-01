package auction.bidders.samples.strategy;

public class ZeroStrategy implements BiddingStrategy {

    @Override
    public int calculateBid(int cash) {
        return 0;
    }

    @Override
    public void showBids(int own, int bid2) {

    }
}
