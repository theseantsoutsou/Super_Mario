package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.HashMap;

public class ConsumableItemManager {
    private static ConsumableItemManager manager;
    private static HashMap<Item, Action> consumeActionMap = new HashMap<>();
    private ConsumableItemManager(){
    }

    public static ConsumableItemManager getInstance(){
        if(manager == null){
            manager = new ConsumableItemManager();
        }
        return manager;
    }
    public void addToHashMap(Item item, Action action){
        consumeActionMap.put(item, action);
    }
    public Action getConsumeAction(Item item){
        return consumeActionMap.get(item);
    }

    public void replenishInventory(Actor actor){
        for(int i = 0;i< actor.getInventory().size(); i++){
            actor.removeItemFromInventory(actor.getInventory().get(i));
        }
        actor.addItemToInventory(new PowerStar(false));
        actor.addItemToInventory(new SuperMushroom(false));
        actor.addItemToInventory(new Wrench());
    }
}
