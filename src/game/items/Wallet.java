package game.items;

/**
 * Wallet class
 * Instead of having individual coin objects in the players inventory,
 * coins can be represented as a integer value in the wallet.
 */
public class Wallet {
    private int credits = 0;
    private static Wallet wallet;

    private Wallet() {
    }

    /**
     * Modifies the number of coins the player has in their wallet
     * + -
     * @param credits
     */
    public void modifyCredits(int credits) {
        this.credits += credits;
    }

    /**
     * returns the number of coins the player has in their wallet
     * @return
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Displays the value stored in the wallet on the UI
     * @return
     */
    public String printCredits() {
        return "$" + this.credits;
    }

    /**
     * Creates an instance of wallet to allow coin values to be represented here
     * @return
     */
    public static Wallet getInstance() {
        if (wallet == null) {
            wallet = new Wallet();
        }
        return wallet;
    }
}
