package game.items;

public interface TradableItem {
    int getValue();
    default void addToInventory(){
        TradableItemInventory.getInstance().appendItem(this);
    }
}
