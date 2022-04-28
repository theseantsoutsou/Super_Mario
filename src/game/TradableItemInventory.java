package game;

import java.util.ArrayList;

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
