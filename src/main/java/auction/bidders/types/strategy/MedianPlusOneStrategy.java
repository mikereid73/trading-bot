package auction.bidders.types.strategy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Bids the median of winning bids plus one. Makes a small initial bid of 1 to get things started.
 *
 * @author Michael Reid
 */
public class MedianPlusOneStrategy extends AbstractStrategy {

    private final List<Integer> winningBidsList = new LinkedList<>();

    @Override
    public int calculateBid(int cash) {
        if (winningBidsList.size() == 0) {
            return 1;
        }

        winningBidsList.sort(Comparator.reverseOrder());
        return (int) (winningBidsList.size() % 2 == 0 ?
                winningBidsList.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBidsList.size() / 2 - 1)
                        .limit(2)
                        .average()
                        .orElse(0.0) :
                winningBidsList.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBidsList.size() / 2)
                        .findFirst()
                        .orElse(0.0)) + 1;
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
