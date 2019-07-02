package auction.bidders.types.strategy;

import java.util.Random;

public class RandomStrategy extends AbstractStrategy {

    private final Random random = new Random();

    @Override
    public int calculateBid(int cash) {
        return random.nextInt(cash + 1);
    }

    @Override
    public void showBids(int own, int other) {

    }
}
