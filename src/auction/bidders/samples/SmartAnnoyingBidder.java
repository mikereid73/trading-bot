package auction.bidders.samples;

public class SmartAnnoyingBidder extends LastPlusOneBidder {

    private int opponentsCash;

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
        opponentsCash = cash;
    }

    @Override
    public int placeBid() {
        if (opponentsCash == 0) {
            return getBidOrGetZero(1);
        }
        return super.placeBid();
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);
        opponentsCash -= other;
    }
}
