package auction.bidders.samples;

import auction.bidders.AbstractBidder;

public class BudgetingBidder extends AbstractBidder {

    private int bid;

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
        bid = cash / (quantity / 2);
    }

    @Override
    public int placeBid() {
        return getBidOrGetZero(bid);
    }
}
