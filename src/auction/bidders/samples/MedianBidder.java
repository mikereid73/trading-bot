package auction.bidders.samples;

import auction.bidders.AbstractBidder;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MedianBidder extends AbstractBidder {

    private List<Integer> opponentWinningBids = new LinkedList<>();

    @Override
    public int placeBid() {
        if(opponentWinningBids.size() == 0) {
            return 1;
        }

        opponentWinningBids.sort(Comparator.reverseOrder());
        int median = (int) (opponentWinningBids.size() % 2 == 0 ?
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

        return getBidOrGetZero(median);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);

        if (other > own) {
            opponentWinningBids.add(other);
        }
    }
}
