package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

import java.util.Random;

/**
 * Bids a pseudorandom amount of cash within it's cash limit which is normally distributed with a caluclated
 * average of the cash divided by the number of rounds.
 * https://en.wikipedia.org/wiki/Normal_distribution
 *
 * @author Michael Reid
 */
public class GaussianRandomBidder extends AbstractBidder {

    private final Random random = new Random();
    private double averageBid;

    @Override
    public void init(int quantity, int cash) {
        super.init(quantity, cash);

        averageBid = cash / (quantity / 2.0);
    }

    @Override
    public int placeBid() {
        // value return by nextGaussian() can be negative, so we use positive version
        int bid = (int) Math.abs((random.nextGaussian() * averageBid) + averageBid);
        return getBidOrGetAll(bid);
    }
}
