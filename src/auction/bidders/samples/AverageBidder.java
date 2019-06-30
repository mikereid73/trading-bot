package auction.bidders.samples;

import auction.bidders.AbstractBidder;

import java.util.LinkedList;
import java.util.List;

public class AverageBidder extends AbstractBidder {

    private List<Integer> opponentWinningBids = new LinkedList<>();

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
    }

    @Override
    public int placeBid() {
        int average = (int) opponentWinningBids.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
                + 1;
        return average <= cash ? average : cash;
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);
        if (other > own) {
            opponentWinningBids.add(other);
        }
    }
}
