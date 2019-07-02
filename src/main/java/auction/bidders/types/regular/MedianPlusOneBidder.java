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

    /**
     * List of all winning bids only.
     */
    private final List<Integer> winningBids = new LinkedList<>();

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);
        winningBids.clear();
    }

    @Override
    public int placeBid() {
        if (winningBids.size() == 0) {
            return 1;
        }

        winningBids.sort(Comparator.reverseOrder());
        int median = (int) (winningBids.size() % 2 == 0 ?
                winningBids.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBids.size() / 2 - 1)
                        .limit(2)
                        .average()
                        .getAsDouble() :
                winningBids.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBids.size() / 2)
                        .findFirst()
                        .getAsDouble()) + 1;

        return getBidOrGetAll(median);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);
        if (own > other) {
            winningBids.add(own);
        } else if (other > own) {
            winningBids.add(other);
        }
    }
}
