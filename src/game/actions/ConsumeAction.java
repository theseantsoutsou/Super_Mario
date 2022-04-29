package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class ConsumeAction extends Action {

    private Item item;
    private Status capability;

    public ConsumeAction (Item item, Status capability) {
        this.item = item;
        this.capability = capability;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this.item);
        actor.addCapability(capability);
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Consume item";
    }
}
