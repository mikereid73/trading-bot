package auction.bidders.types.strategy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MedianPlusOneStrategy extends AbstractStrategy {

    private final List<Integer> winningBids = new LinkedList<>();

    @Override
    public int calculateBid(int cash) {
        if(winningBids.size() == 0) {
            return 1;
        }

        winningBids.sort(Comparator.reverseOrder());
        return (int) (winningBids.size() % 2 == 0 ?
                winningBids.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBids.size() / 2 - 1)
                        .limit(2)
                        .average()
                        .orElse(0.0) :
                winningBids.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(winningBids.size() / 2)
                        .findFirst()
                        .orElse(0.0)) + 1;
    }

    @Override
    public void showBids(int own, int other) {
        if (other > own) {
            winningBids.add(other);
        }
    }
}
