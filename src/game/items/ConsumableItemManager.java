package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The TradableItemManager class is a class that manages the consumable items and their specific associated actions.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class ConsumableItemManager {
    //Private attributes
    private static ConsumableItemManager manager;

    public ArrayList<ConsumableItem> consumableItems = new ArrayList<ConsumableItem>();

    /**
     * Private constructor
     */
    private ConsumableItemManager(){
    }

    /**
     * Public static method to instantiate the TradableItemManager if it does not exist,
     * otherwise returns preexisting instance.
     *
     * @return manager, a TradableItemManager instance
     */
    public static ConsumableItemManager getInstance(){
        if(manager == null){
            manager = new ConsumableItemManager();
        }
        return manager;
    }
    public void appendItem(ConsumableItem item){this.consumableItems.add(item);}
    /**
     * Replenish actor's inventory with new instances of tradable items.
     *
     * @param actor Actor whose inventory is to be replenished (i.e. Toad)
     */
}
