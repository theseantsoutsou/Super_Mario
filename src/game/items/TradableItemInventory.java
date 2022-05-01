package game.items;

import java.util.ArrayList;

/**
 * Toad's inventory
 * What items Toad may have for sale when the player comes to the to trade
 */
public class TradableItemInventory {
    private ArrayList<TradableItem> tradableItems;
    private static TradableItemInventory inventory;
    private TradableItemInventory() {tradableItems = new ArrayList<>();}
    public static TradableItemInventory getInstance() {
        if (inventory == null) {
            inventory = new TradableItemInventory();
        }
        return inventory;
    }
    public ArrayList<TradableItem> getTradableItems() {
        return tradableItems;
    }
    public void appendItem(TradableItem item){
        tradableItems.add(item);
    }
}
