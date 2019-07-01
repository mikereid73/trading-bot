package auction.bidders.samples.strategy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MedianPlusOneStrategy implements BiddingStrategy {

    private List<Integer> opponentWinningBids = new LinkedList<>();

    @Override
    public int calculateBid(int cash) {
        if(opponentWinningBids.size() == 0) {
            return 1;
        }

        opponentWinningBids.sort(Comparator.reverseOrder());
        return (int) (opponentWinningBids.size() % 2 == 0 ?
                opponentWinningBids.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(opponentWinningBids.size() / 2 - 1)
                        .limit(2)
                        .average()
                        .getAsDouble() :
                opponentWinningBids.stream()
                        .mapToDouble(Integer::intValue)
                        .skip(opponentWinningBids.size() / 2)
                        .findFirst()
                        .getAsDouble()) + 1;
    }

    @Override
    public void showBids(int own, int other) {
        if (other > own) {
            opponentWinningBids.add(other);
        }
    }
}
