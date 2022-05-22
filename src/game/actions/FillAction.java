package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Water;

/**
 * FillAction extends Action. Class responsible for logic that allows an actor to store Water objects in the Bottle item.
 * @author Connor McCloud-Gibson
 * @since 17/05/22
 */
public class FillAction extends Action {
    private Bottle bottle;
    private Water water;

    /**
     * Constructor
     * @param water to fill the bottle with
     * @param bottle to be filled
     */
    public FillAction(Water water, Bottle bottle){
        this.water= water;
        this.bottle = bottle;
    }

    /**
     * Execute the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing the execution of the action.
     */
    public String execute(Actor actor, GameMap map) {
        this.bottle.fill(water);
        return actor.toString() + " fills bottle with " + water.toString();
    }

    /** Supplies appropriate descriptor for action.
     *
     * @param actor The actor performing the action.
     * @return a String to add to actor's menu of options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " fills bottle.";
    }
}
