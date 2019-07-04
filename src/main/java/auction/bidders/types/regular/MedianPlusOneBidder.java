package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Bids the median of winning bids plus one. Makes a small initial bid of 1 to get things started.
 * If it cannot afford a bid, it goes all in regardless.
 *
 * @author Michael Reid
 */
public class MedianPlusOneBidder extends AbstractBidder {

    /* A list of winning bids made by either bidder */
    private final List<Integer> winningBidsList = new LinkedList<>();

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
        winningBidsList.clear();
    }

    @Override
    public int placeBid() {
        // cannot calculate median if no one has won yet
        if (winningBidsList.size() == 0) {
            return 1;
        }

        winningBidsList.sort(Comparator.reverseOrder());
        int median = (int) (winningBidsList.size() % 2 == 0 ?
                winningBidsList.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBidsList.size() / 2 - 1)
                        .limit(2)
                        .average()
                        .getAsDouble() :
                winningBidsList.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBidsList.size() / 2)
                        .findFirst()
                        .getAsDouble()) + 1;

        return getBidOrGetAll(median);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);
        if (own >= other) {
            winningBidsList.add(own);
        } else if (other >= own) {
            winningBidsList.add(other);
        }
    }
}
