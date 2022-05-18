package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

public interface Consumable {
    default void addToConsumablesManager(){
        ConsumableItemManager.getInstance().appendItem(this);
    }
    void applyEffects(Actor actor);
}
