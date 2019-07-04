package auction.bidders.types.strategy;

import java.util.Random;

/**
 * Bids a random amount of cash within it's cash limit.
 *
 * @author Michael Reid
 */
public class RandomStrategy extends AbstractStrategy {

    /* Used to generate random bid values */
    private final Random random = new Random();

    @Override
    public int calculateBid(int cash) {
        return random.nextInt(cash + 1);
    }

    @Override
    public void showBids(int own, int other) {
        // don't care
    }
}
