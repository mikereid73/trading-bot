package auction.bidders.samples.strategy;

public class LastPlusOneStratgey implements BiddingStrategy {

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
