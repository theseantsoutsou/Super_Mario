package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Jumpable;

/**
 * The JumpAction class is a special Action for jumping onto high-grounds.
 * The JumpAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class JumpAction extends Action{
    //Private attributes
    private Jumpable target; //High-ground that is to be jumped on

    private String direction;

    /**
     * Constructor.
     *
     * @param target    the target jumpable ground to jump on
     * @param direction the direction of the target is at relative to the actor executing the action
     */
    public JumpAction(Jumpable target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the JumpAction.
     * Checks if player has 'grown' and get the jump success rate of the target.
     * If the target has grown or passes the success rate, move actor to the target's location and add a status of
     * ON_HIGH_GROUND to actor's capabilities.
     * Otherwise, actor takes fall damage, value depending on the target.
     * Check if actor is alive after taking fall damage.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a String describing the outcome of the JumpAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random r = new Random();
        boolean isTall = actor.hasCapability(Status.TALL);
        int successRate = this.target.getSuccessRate();
        String result;


        if (isTall || r.nextInt(100) <= successRate) {
            map.moveActor(actor, this.getTargetLocation(actor, map));
            result = actor + " jumped to " + this.target.getClass().getSimpleName() + " successfully.";
            actor.addCapability(Status.ON_HIGH_GROUND);

        } else {
            int fallDamage = this.target.getFallDamage();
            actor.hurt(fallDamage);
            result = actor + " failed to jump to " + this.target.getClass().getSimpleName() + ". Player receives " + fallDamage + " fall damage.";
            if (!actor.isConscious()) {
                map.removeActor(actor);
                result += System.lineSeparator() + "Player died from fall damage.";
            }

        }
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
            if (exit.getName().equals(this.direction)) {
                return exit.getDestination();
            }
        }
        return null;
    }

    /**
     * Display description of what high-ground the player can jump on and in what direction.
     *
     * @param actor The actor performing the action.
     * @return a String to add to actor's menu of options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + this.target.getClass().getSimpleName() + " at " + direction;
    }
}
