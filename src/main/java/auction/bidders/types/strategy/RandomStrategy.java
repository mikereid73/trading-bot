package auction.bidders.types.strategy;

import java.util.Random;

/**
 * Bids a random amount of cash.
 *
 * @author Michael Reid
 */
public class RandomStrategy extends AbstractStrategy {

    /** Random number generator **/
    private final Random random = new Random();

    @Override
    public int calculateBid(int cash) {
        return random.nextInt(cash + 1);
    }

    @Override
    public void showBids(int own, int other) {

    }
}
