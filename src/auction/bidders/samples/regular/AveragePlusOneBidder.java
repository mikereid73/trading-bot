package auction.bidders.samples.regular;

import auction.bidders.AbstractBidder;

import java.util.LinkedList;
import java.util.List;

public class AveragePlusOneBidder extends AbstractBidder {

    private List<Integer> winningBids = new LinkedList<>();

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
        winningBids.clear();
    }

    @Override
    public int placeBid() {
        int average = (int) winningBids.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
                + 1;
        return getBidOrGetZero(average);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);
        if (own > other) {
            winningBids.add(own);
        } else if(other > own) {
            winningBids.add(other);
        }
    }
}
