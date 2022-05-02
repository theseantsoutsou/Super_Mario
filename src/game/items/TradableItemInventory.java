package game.items;

import java.util.ArrayList;

/**
 * Singleton class used for tracking TradeableItems
 * Can be used to access methods and attributes of TradeableItems
 * Enforces DIP principle.
 */
public class TradableItemInventory {
    private ArrayList<TradableItem> tradableItems;
    private static TradableItemInventory inventory;

    /**
     * Private constructor for Singleton pattern.
     */
    private TradableItemInventory() {tradableItems = new ArrayList<>();}

    /**
     * Instantiate the TradeableItemInventory if it does not exist,
     * else returns preexisting instance.
     * @return inventory
     */
    public static TradableItemInventory getInstance() {
        if (inventory == null) {
            inventory = new TradableItemInventory();
        }
        return inventory;
    }

    /**
     * Getter
     *
     * Returns list of TradableItems
     * @return tradeableItems
     */
    public ArrayList<TradableItem> getTradableItems() {
        return tradableItems;
    }
    public void appendItem(TradableItem item){
        tradableItems.add(item);
    }
}
