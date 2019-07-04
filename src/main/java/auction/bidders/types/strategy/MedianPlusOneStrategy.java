package auction.bidders.types.strategy;

import java.util.LinkedList;
import java.util.List;

/**
 * Bids the median value of winning bids plus one. We start by bidding the an average amount,
 * the available cash divided by the total number of bidding rounds. It will keep track of the winning bids
 * made in each round and use this to calculate a bidding amount which will be the mean value in the list
 * plus 1 to try and be aggressive. It will consider a draw to be a win as the bidder still gets a partial reward
 * which is better than nothing.
 * <p>
 * Returns -1 if it cannot calculate an median value (empty list)
 *
 * @author Michael Reid
 */
public class MedianPlusOneStrategy extends AbstractStrategy {

    /* A list of winning bids made by either bidder */
    private final List<Integer> winningBidsList = new LinkedList<>();

    @Override
    public int calculateBid(int cash) {
        if (winningBidsList.size() == 0) {
            return -1;
        }

        // if the list has an even number of elements, take the average of the middle two values
        // if the list has an odd number of elements, take the middle value
        return (int) (winningBidsList.size() % 2 == 0 ?
                winningBidsList
                        .stream()
                        .sorted()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBidsList.size() / 2 - 1)
                        .limit(2)
                        .average()
                        .getAsDouble() :
                winningBidsList.stream()
                        .sorted()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBidsList.size() / 2)
                        .findFirst()
                        .getAsDouble()) + 1;
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
