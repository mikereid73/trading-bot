package auction.bidders.samples.strategy;

import java.util.LinkedList;
import java.util.List;

public class AveragePlusOneStrategy extends AbstractStrategy {

    private List<Integer> opponentswinningBids = new LinkedList<>();

    @Override
    public int calculateBid(int cash) {
        return (int) opponentswinningBids.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
                + 1;
    }

    @Override
    public void showBids(int own, int other) {
        if(other > own) {
            opponentswinningBids.add(other);
        }
    }
}
