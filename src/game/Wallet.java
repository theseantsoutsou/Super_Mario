package game;

public class Wallet {
    private int credits = 0;
    private static Wallet wallet;

    private Wallet() {
    }

    public void modifyCredits(int credits) {
        this.credits += credits;
    }

    public int getCredits() {
        return credits;
    }

    public static Wallet getInstance() {
        if (wallet == null) {
            wallet = new Wallet();
        }
        return wallet;
    }
}
