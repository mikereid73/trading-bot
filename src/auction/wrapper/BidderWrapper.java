package auction.wrapper;

import auction.bidders.Bidder;

public class BidderWrapper implements Comparable<BidderWrapper> {

    private final String name;

    private final Bidder bidder;

    private int auctions;

    private int wins;

    private int losses;

    private int draws;

    private int winningStreak;

    private int longestWinningStreak;

    public BidderWrapper(String name, Bidder bidder) {
        this.name = name;
        this.bidder = bidder;
    }

    public void recordWin() {
        auctions++;
        wins++;
        winningStreak++;
    }

    public void recordLoss() {
        auctions++;
        losses++;
    }

    public void recordDraw() {
        auctions++;
        draws++;
        longestWinningStreak = Math.max(winningStreak, longestWinningStreak);
        winningStreak = 0;
    }

    public String getName() {
        return name;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public int getAuctions() {
        return auctions;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getWinningStreak() {
        return winningStreak;
    }

    public int getLongestWinningStreak() {
        return longestWinningStreak;
    }

    @Override
    public int compareTo(BidderWrapper other) {
        //TODO: fix this screwed up compare logic
        if (other == null) return 1;
        else if (other == this) return 0;
        return Integer.compare(wins, other.wins);
    }
}
