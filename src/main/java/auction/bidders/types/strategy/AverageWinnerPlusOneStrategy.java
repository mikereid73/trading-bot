package auction.bidders.types.strategy;

import java.util.LinkedList;
import java.util.List;

/**
 * This strategy bids the average of the winning bids plus 1. We start by bidding the an average amount,
 * the available cash divided by the total number of bidding rounds. It will keep track of the winning bids
 * made in each round and use this to calculate a bidding amount which will be the average value in the list
 * plus 1 to try and be aggressive. It will consider a draw to be a win as the bidder still gets a partial reward
 * which is better than nothing.
 *
 * @author Michael Reid
 */
public class AverageWinnerPlusOneStrategy extends AbstractStrategy {

    /* A list of winning bids made by either bidder */
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
        // a win and a draw give us some reward, store both
        if (own >= other) {
            winningBidsList.add(own);
        } else if (other >= own) {
            winningBidsList.add(other);
        }
    }
}
