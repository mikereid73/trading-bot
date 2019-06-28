package auction;

public class BidderScoreCard implements Comparable<BidderScoreCard> {

    private int quantity;
    private int cash;

    public BidderScoreCard(int quantity, int cash) {
        this.quantity = quantity;
        this.cash = cash;
    }

    public void addQauntity(int quantity) {
        this.quantity += quantity;
    }

    public void removeCash(int cash) {
        this.cash -= cash;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCash() {
        return cash;
    }

    @Override
    public int compareTo(BidderScoreCard other) {
        if(other == null) return -1;
        else if(quantity > other.quantity) return 1;
        else if(quantity < other.quantity) return -1;
        else if(cash > other.cash) return 1;
        else if(cash < other.cash) return -1;
        else return 0;
    }
}
