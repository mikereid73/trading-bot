package auction.bidders.samples;

import auction.bidders.AbstractBidder;

import java.util.Random;

public class RandomBidder extends AbstractBidder {

    protected final Random random = new Random();

    @Override
    public int placeBid() {
        return random.nextInt(cash + 1);
    }
}
