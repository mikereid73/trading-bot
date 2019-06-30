package auction.wrapper;

import auction.bidders.Bidder;

/**
 * User is a wrapper for a Bidder to to keep track of their progress. This includes tracking
 * their wins, loss, draws, and winning streak.
 *
 * @author Michael Reid
 */
public class User implements Comparable<User> {

    /**
     * Users name
     **/
    private final String name;

    /**
     * The bidder implementation used
     **/
    private final Bidder bidder;

    /**
     * Number of auctions taken part int
     **/
    private int auctions;

    /**
     * Number of wins at an auction
     **/
    private int wins;

    /**
     * Number of losses at an auction
     **/
    private int losses;

    /**
     * Number of draws at an auction
     **/
    private int draws;

    /**
     * Current amount of consecutive wins
     **/
    private int currentWinningStreak;

    /**
     * Longest run of consecutive wins
     **/
    private int longestWinningStreak;

    /**
     * Create a new User with a name and Bidder implementation
     *
     * @param name   the User name
     * @param bidder the bidder implementation
     */
    public User(String name, Bidder bidder) {
        this.name = name;
        this.bidder = bidder;
    }

    /**
     * Logs a win for this User and updates their winning streak.
     */
    public void recordWin() {
        auctions++;
        wins++;
        currentWinningStreak++;
        longestWinningStreak = Math.max(currentWinningStreak, longestWinningStreak);
    }

    /**
     * Logs a loss for this User and resets their winning streak.
     */
    public void recordLoss() {
        auctions++;
        losses++;
        longestWinningStreak = Math.max(currentWinningStreak, longestWinningStreak);
        currentWinningStreak = 0;
    }

    /**
     * Logs a draw for this User and resets their winning streak.
     */
    public void recordDraw() {
        auctions++;
        draws++;
        longestWinningStreak = Math.max(currentWinningStreak, longestWinningStreak);
        currentWinningStreak = 0;
    }

    /**
     * @return the name of the User
     */
    public String getName() {
        return name;
    }

    /**
     * @return the Bidder implementation
     */
    public Bidder getBidder() {
        return bidder;
    }

    /**
     * @return the number of Auctions taken part in
     */
    public int getAuctions() {
        return auctions;
    }

    /**
     * @return the number of wins recorded in an Auction
     */
    public int getWins() {
        return wins;
    }

    /**
     * @return the number of losses recorded in an Auction
     */
    public int getLosses() {
        return losses;
    }

    /**
     * @return the number of draws recorded in an Auction
     */
    public int getDraws() {
        return draws;
    }

    /**
     * @return the longest winning streak recorded in an Auction
     */
    public int getLongestWinningStreak() {
        return longestWinningStreak;
    }

    /**
     * Determines the winner between to Users. A user wins if they have recorded more wins. If they have recorded
     * the same amount of wins, then the User with fewer losses is the winner.
     *
     * @param other The user to compare against
     * @return 1 if won, 0 if draw, -1 if loss
     */
    @Override
    public int compareTo(User other) {
        if (other == null) return 1;
        if (other == this) return 0;
        if (wins > other.wins) return 1;
        if (wins == other.wins) {
            return Integer.compare(losses, other.losses);
        }
        return -1;
    }
}
