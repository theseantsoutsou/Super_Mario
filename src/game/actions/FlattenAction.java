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
 * The FlattenAction class is a special Action for crushing high-grounds and turning them into dirt.
 * The FlattenAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class FlattenAction extends Action {
    //Private attributes
    private Jumpable target; //High-ground that is to be jumped on

    private String direction;

    /**
     * Constructor.
     *
     * @param target the Jumpable ground to jump on
     */
    public FlattenAction (Jumpable target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes FlattenAction.
     * Moves actor to target's location and set the ground at the location to dirt.
     * Spawn a new Coin item at the flattened location.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return string describing the result of the FlattenAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location targetLocation = this.getTargetLocation(actor, map);
        map.moveActor(actor, targetLocation);
        targetLocation.setGround(new Dirt());
        targetLocation.addItem(new Coin(targetLocation, 5));
        String result = actor + " flattened " + this.target.getClass().getSimpleName() + ".";

        return result;
    }

    /**
     * Checks the exits around the actor and return the exit location at the desired direction.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return the location at of the exit in the desired direction
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
     * Display description of what high-ground the player can flatten and in what direction.
     *
     * @param actor The actor performing the action.
     * @return a String to add to actor's menu of options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " flattens " + this.target.getClass().getSimpleName() + " at " + direction;
    }




}
