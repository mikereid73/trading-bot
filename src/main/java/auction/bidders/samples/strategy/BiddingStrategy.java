package auction.bidders.samples.strategy;

public interface BiddingStrategy {

    int calculateBid(int cash);
    void showBids(int own, int other);
}
