package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

import java.util.LinkedList;
import java.util.List;

/**
 * This bidder bids the average of the winning bids plus 1. We start by bidding the an average amount,
 * the available cash divided by the total number of bidding rounds. It will keep track of the winning bids
 * made in each round and use this to calculate a bidding amount which will be the average value in the list
 * plus 1 to try and be aggressive. It will consider a draw to be a win as the bidder still gets a partial reward
 * which is better than nothing.
 *
 * @author Michael Reid
 */
public class AverageWinnerPlusOneBidder extends AbstractBidder {

    /* A list of winning bids made by either bidder */
    private final List<Integer> winningBidsList = new LinkedList<>();

    /* The total number of bidding rounds in the acution */
    private int totalBiddingRounds;

    /* The current bidding round in the auction */
    private int currentBiddingRound;

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);

        winningBidsList.clear();
        totalBiddingRounds = quantity / 2;
    }

    @Override
    public int placeBid() {
        // new round
        currentBiddingRound++;

        // initial bid, be somewhat aggressive
        if (currentBiddingRound == 1) {
            return getBidOrGetAll(cash / totalBiddingRounds);
        }

        int averagePlusOne = (int) winningBidsList.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
                + 1;
        return getBidOrGetAll(averagePlusOne);
    }

    @Override
    public void bids(int own, int other) {
        super.bids(own, other);

        // a win and a draw give us some reward, store both
        if (own >= other) {
            winningBidsList.add(own);
        } else if (other >= own) {
            winningBidsList.add(other);
        }
    }
}
