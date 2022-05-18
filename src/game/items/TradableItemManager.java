package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The TradableItemInventory is a singleton class used to track TradableItems.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class TradableItemManager {
    //Private attributes
    private ArrayList<TradableItem> tradableItems;
    private static TradableItemManager inventory;

    /**
     * Private constructor for Singleton pattern.
     */
    private TradableItemManager() {tradableItems = new ArrayList<>();}

    /**
     * Public static method to instantiate the TradableItemInventory if it does not exist,
     * otherwise returns preexisting instance.
     * @return inventory
     */
    public static TradableItemManager getInstance() {
        if (inventory == null) {
            inventory = new TradableItemManager();
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

    public void replenishInventory(Actor actor){
        List<Item> actorInventory = actor.getInventory();
        Boolean contains = false;
        if(actorInventory.isEmpty()){
            actor.addItemToInventory(new PowerStar(false));
            actor.addItemToInventory(new SuperMushroom(false));
            actor.addItemToInventory(new Wrench());
        }
        else {
            int size = actorInventory.size();
            for (int i = 0; i < size; i++) {
                contains = actorInventory.get(i) instanceof PowerStar;
                if(contains){break;}
            }
            if (!contains) actor.addItemToInventory(new PowerStar(false));
            for (int i = 0; i < size; i++) {
                contains = actorInventory.get(i) instanceof SuperMushroom;
                if(contains){break;}
            }
            if (!contains) actor.addItemToInventory(new SuperMushroom(false));
        }
    }
}
