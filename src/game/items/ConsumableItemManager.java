package game.items;

import java.util.ArrayList;

/**
 * The ConsumableItemManager class is a singleton class that manages the consumable items and their
 * specific associated actions.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class ConsumableItemManager {
    //Private attributes
    /**
     * static Singleton instance
     */
    private static ConsumableItemManager manager;
    /**
     * Array of Consumable type
     */

    public ArrayList<Consumable> consumableItems = new ArrayList<Consumable>();

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

    /**
     * Add item to the ArrayList
     * @param item
     */
    public void appendItem(Consumable item){this.consumableItems.add(item);}

}
