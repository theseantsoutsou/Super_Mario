package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.actions.ConsumeAction;

public interface ConsumableItem {
    default void addToConsumablesManager(){
        ConsumableItemManager.getInstance().appendItem(this);
    }
    void applyEffects(Actor actor);
    Enum<Status> getCapability();
}
