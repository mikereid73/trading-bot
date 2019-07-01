package auction.bidders.samples.strategy;

public class BadStrategy implements BiddingStrategy {

    @Override
    public int calculateBid(int cash) {
        return 0;
    }

    @Override
    public void showBids(int own, int bid2) {

    }
}
