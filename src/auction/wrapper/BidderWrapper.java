package auction.wrapper;

import auction.bidders.Bidder;

public class BidderWrapper implements Comparable<BidderWrapper> {

    private final String name;

    private final Bidder bidder;

    private int wins;

    private int losses;

    private int winningStreak;

    private int longestWinningStreak;

    public BidderWrapper(String name, Bidder bidder) {
        this.name = name;
        this.bidder = bidder;
    }

    public void recordWin() {
        wins++;
        winningStreak++;
    }

    public void recordLoss() {
        losses++;
    }

    public void recordDraw() {
        longestWinningStreak = Math.max(winningStreak, longestWinningStreak);
        winningStreak = 0;
    }

    public String getName() {
        return name;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getWinningStreak() {
        return winningStreak;
    }

    public int getLongestWinningStreak() {
        return longestWinningStreak;
    }

    @Override
    public int compareTo(BidderWrapper other) {
        if (other == null) return 1;
        else if (other == this) return 0;
        return Integer.compare(wins, other.wins);
    }
}
