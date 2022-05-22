package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
/**
 * Interface implemented by Items which can be consumed by an Actor.
 */

public interface Consumable {
    /**
     * Default method which adds the instance of Consumable to the ConsumableItemManager
     */
    default void addToConsumablesManager(){
        ConsumableItemManager.getInstance().appendItem(this);
    }

    /**
     * Abstract method for applying the effects of the Consumable Item
     * @param actor
     */
    void applyEffects(Actor actor);
}
