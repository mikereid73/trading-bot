package auction.bidders.samples;

import auction.bidders.AbstractBidder;

public class BudgetingBidder extends AbstractBidder {

    private int bid;

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);

        int rounds = quantity / 2;
        bid = cash / rounds;
    }

    @Override
    public int placeBid() {
        return getBidOrGetZero(bid);
    }
}
