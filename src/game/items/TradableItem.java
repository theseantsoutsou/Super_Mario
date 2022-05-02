package game.items;

/**
 * The TradableItem interface is an interface that manages items that can be traded.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public interface TradableItem {
    /**
     * Getter for item's value.
     *
     * @return value of TradableItem
     */
    int getValue();

    /**
     * Default method adds instance of a TradableItem to the TradableItemInventory singleton instance.
     * Use in the constructor of TradableItems.
     */
    default void addToInventory(){
        TradableItemInventory.getInstance().appendItem(this);
    }
}
