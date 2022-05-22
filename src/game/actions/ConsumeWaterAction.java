package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

/**
 * ConsumeWaterAction is a class that extends Action, which implements the logic for consuming Water specifically.
 * @author Connor McCloud-Gibson
 * @since 18/05/22
 */
public class ConsumeWaterAction extends Action {
    /**
     * The Bottle object which contains the water objects to be consumed
     */
    private Bottle item;
    /**
     * Constructor for the ConsumeWaterAction class.
     *
     * @param item the Bottle containing the water to be consumed.
     */
    public ConsumeWaterAction(Bottle item) {
        this.item = item;
    }

    /**
     * Actor drinks the water in the top of the stack of the Bottle.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String water = this.item.drink(actor);
        return actor + " consumed " + water;
    }

    /** Supplies appropriate descriptor for action.
     *
     * @param actor The actor performing the action.
     * @return a String to add to actor's menu of options
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.item;
    }
}
