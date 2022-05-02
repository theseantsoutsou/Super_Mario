package game.items;

import java.util.ArrayList;

/**
 * The TradableItemInventory is a singleton class used to track TradableItems.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class TradableItemInventory {
    //Private attributes
    private ArrayList<TradableItem> tradableItems;
    private static TradableItemInventory inventory;

    /**
     * Private constructor for Singleton pattern.
     */
    private TradableItemInventory() {tradableItems = new ArrayList<>();}

    /**
     * Public static method to instantiate the TradableItemInventory if it does not exist,
     * otherwise returns preexisting instance.
     * @return inventory
     */
    public static TradableItemInventory getInstance() {
        if (inventory == null) {
            inventory = new TradableItemInventory();
        }
        return inventory;
    }

    /**
     * Getter for the tradableItems
     *
     * @return a list of tradableItems
     */
    public ArrayList<TradableItem> getTradableItems() {
        return tradableItems;
    }

    /**
     * Adds a TradableItem to the list of tradableItems
     *
     * @param item a TradableItem
     */
    public void appendItem(TradableItem item){
        tradableItems.add(item);
    }
}
