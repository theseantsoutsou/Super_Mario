package game.items;

/**
 * Interface for trading
 * gets the value of items listed for trade
 * adds traded items to the inventory of the player
 */
public interface TradableItem {
    int getValue();
    default void addToInventory(){
        TradableItemInventory.getInstance().appendItem(this);
    }
}
