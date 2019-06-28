package auction.bidders;

public abstract class AbstractBidder implements Bidder{

    protected int quantity;
    protected int cash;

    @Override
    public void init(int quantity, int cash) {
        this.quantity = quantity;
        this.cash = cash;
    }

    @Override
    public void bids(int own, int other) {
        cash -= own;
    }
}
