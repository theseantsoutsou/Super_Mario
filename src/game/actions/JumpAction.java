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
 * Special Action that allows the player to move from lower ground to higher ones
 */
public class JumpAction extends Action{
    //Private Attributes
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
    public JumpAction(Jumpable target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Takes the terrain feature that is at the location the player wants to move to
     * retrieves the probability of the player jumping onto the terrain feature (stored in the class itself)
     * does a check to see if the player succeeds in the dice roll
     * jumps if success, takes damage if fail (if hp goes to 0, player dies from fall damage)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random r = new Random();
        Boolean isTall = actor.hasCapability(Status.TALL);
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
     * Get the coordinates of the terrain feature the player is attempting to jump on to
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
     * Display the description of jumping on to high ground on the menu
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + this.target.getClass().getSimpleName() + " at " + direction;
    }
}
