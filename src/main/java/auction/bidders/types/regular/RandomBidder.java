package auction.bidders.types.regular;

import auction.bidders.AbstractBidder;

import java.util.Random;

/**
 * Bids a random amount of cash within it's cash limit.
 *
 * @author Michael Reid
 */
public class RandomBidder extends AbstractBidder {

    /* Used to generate random bid values */
    private final Random random = new Random();

    @Override
    public int placeBid() {
        return random.nextInt(cash + 1);
    }
}
