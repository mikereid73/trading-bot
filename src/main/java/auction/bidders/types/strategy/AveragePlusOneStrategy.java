package auction.bidders.types.strategy;

import java.util.LinkedList;
import java.util.List;

/**
 * Bids the average of winning bids plus one. Makes a small initial bid of 1 to get things started.
 *
 * @author Michael Reid
 */
public class AveragePlusOneStrategy extends AbstractStrategy {

    private final List<Integer> winningBidsList = new LinkedList<>();

    @Override
    public int calculateBid(int cash) {
        return (int) winningBidsList.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
                + 1;
    }

    @Override
    public void showBids(int own, int other) {
        if (own > other) {
            winningBidsList.add(own);
        } else if (other > own) {
            winningBidsList.add(other);
        }
    }
}
