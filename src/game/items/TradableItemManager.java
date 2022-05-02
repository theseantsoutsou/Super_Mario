package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.HashMap;

/**
 * The TradableItemManager class is a class that manages the consumable items and their specific associated actions.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class TradableItemManager {
    //Private attributes
    private static TradableItemManager manager;
    private static HashMap<Item, Action> consumeActionMap = new HashMap<>();

    /**
     * Private constructor
     */
    private TradableItemManager(){
    }

    /**
     * Public static method to instantiate the TradableItemManager if it does not exist,
     * otherwise returns preexisting instance.
     *
     * @return manager, a TradableItemManager instance
     */
    public static TradableItemManager getInstance(){
        if(manager == null){
            manager = new TradableItemManager();
        }
        return manager;
    }

    /**
     * Add a pair of item and associated action (currently just ConsumeAction) to the consumeActionMap
     *
     * @param item Consumable item (key)
     * @param action Action to be paired with (value)
     */
    public void addToHashMap(Item item, Action action){
        consumeActionMap.put(item, action);
    }

    /**
     * Gets the Action locked by the item in consumeActionMap
     *
     * @param item the key to access the value in consumeActionMap
     * @return The Action stored with the item key in consumeActionMap
     */
    public Action getConsumeAction(Item item){
        return consumeActionMap.get(item);
    }

    /**
     * Replenish actor's inventory with new instances of tradable items.
     *
     * @param actor Actor whose inventory is to be replenished (i.e. Toad)
     */
    public void replenishInventory(Actor actor){
        for(int i = 0;i< actor.getInventory().size(); i++){
            actor.removeItemFromInventory(actor.getInventory().get(i));
        }
        actor.addItemToInventory(new PowerStar(false));
        actor.addItemToInventory(new SuperMushroom(false));
        actor.addItemToInventory(new Wrench());
    }
}
