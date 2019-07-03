package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

import java.util.LinkedList;
import java.util.List;

/**
 * Bids the average of winning bids plus one. Makes a small initial bid of 1 to get things started.
 * If it cannot afford a bid, it goes all in regardless.
 *
 * @author Michael Reid
 */
public class AveragePlusOneBidder extends AbstractBidder {

    private final List<Integer> winningBidsList = new LinkedList<>();

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
        winningBidsList.clear();
    }

    @Override
    public int placeBid() {
        int average = (int) winningBidsList.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
                + 1;
        return getBidOrGetAll(average);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);
        if (own > other) {
            winningBidsList.add(own);
        } else if (other > own) {
            winningBidsList.add(other);
        }
    }
}
