package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

import java.util.Random;

/**
 * Bids a random amount of cash.
 *
 * @author Michael Reid
 */
public class RandomBidder extends AbstractBidder {

    /** Random number generator **/
    private final Random random = new Random();

    @Override
    public int placeBid() {
        return random.nextInt(cash + 1);
    }
}
