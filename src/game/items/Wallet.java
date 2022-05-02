package game.items;

/**
 * The Wallet is a singleton class used to store the total value of Coins the player has acquired.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Wallet {
    private int credits = 0;
    private static Wallet wallet;

    /**
     * Private constructor
     */
    private Wallet() {
    }

    /**
     * Modifies the number of coins the player has in their wallet
     * + -
     *
     * @param credits The value to be added to the wallet.
     */
    public void modifyCredits(int credits) {
        this.credits += credits;
    }

    /**
     * Getter for the total value of coins the player has in the Wallet.
     *
     * @return An integer representation of the total value of coins the Wallet contains.
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Displays the value stored in the wallet on the UI
     *
     * @return The value of coins stored in the Wallet formatted with '$'.
     */
    public String printCredits() {
        return "$" + this.credits;
    }

    /**
     * Public static method to instantiate the Wallet if it does not exist,
     * otherwise returns preexisting instance.
     *
     * @return wallet
     */
    public static Wallet getInstance() {
        if (wallet == null) {
            wallet = new Wallet();
        }
        return wallet;
    }
}
