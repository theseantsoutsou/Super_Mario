package game.items;

/**
 * Interface for trading
 * gets the value of items listed for trade
 * adds traded items to the TradeableItemInventory singleton class.
 */
public interface TradableItem {
    int getValue();

    /**
     * Default method adds instance of TradeableItem to inventory.
     * Use in the constructor of TradeableItems.
     */
    default void addToInventory(){
        TradableItemInventory.getInstance().appendItem(this);
    }
}
