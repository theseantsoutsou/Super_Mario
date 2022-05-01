package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * Class that allows the player to use a magic Item (Power Star or Super Mushroom)
 */
public class ConsumeAction extends Action {

    private Item item;
    private Status capability;

    /**
     * Constructor
     * @param item
     * @param capability
     */
    public ConsumeAction (Item item, Status capability) {
        this.item = item;
        this.capability = capability;
    }

    /**
     * Adds associated capability to player when magic item is consumed
     * removes item from the player's inventory
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this.item);
        actor.addCapability(capability);
        if (this.capability == Status.TALL) {
            actor.increaseMaxHp(50);
        }
        else if (this.capability == Status.POWER_STAR) {
            actor.heal(200);
        }
        return actor + " consumed a " + this.item;
    }

    /**
     * Display description of what item the player uses/consumes
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.item;
    }
}
