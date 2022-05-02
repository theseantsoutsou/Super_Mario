package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Dirt;
import game.grounds.Jumpable;
import game.items.Coin;

import java.util.Random;

/**
 * Action for when the player flattens high ground when using a Power Star
 */
public class FlattenAction extends Action {
    /**
     * The Ground that is to be attacked
     */
    private Jumpable target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public FlattenAction (Jumpable target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * gets the location of that the player is trying to move to
     * moves the player there
     * 'flattens' the high ground by converting it to dirt
     * creates a coin in the place that was flatten
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location targetLocation = this.getTargetLocation(actor, map);
        map.moveActor(actor, targetLocation);
        targetLocation.setGround(new Dirt());
        targetLocation.addItem(new Coin(targetLocation));
        String result = actor + " flattened " + this.target.getClass().getSimpleName() + ".";

        return result;

    }

    /**
     * get the coordinates of where the player is trying to go
     * @param actor
     * @param map
     * @return
     */
    public Location getTargetLocation(Actor actor, GameMap map) {

        Location actorLocation = map.locationOf(actor);
        for (Exit exit: actorLocation.getExits()) {
            if (exit.getName() == this.direction) {
                return exit.getDestination();
            }
        }
        return null;
    }

    /**
     * Display the description for the prompt to flatten a piece of high ground
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " flattens " + this.target.getClass().getSimpleName() + " at " + direction;
    }




}
