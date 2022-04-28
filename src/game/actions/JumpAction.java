package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Jumpable;

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

    @Override
    public String execute(Actor actor, GameMap map) {
        Random r = new Random();
        Boolean isTall = actor.hasCapability(Status.TALL);
        int successRate = this.target.getSuccessRate();
        String result;


        if (isTall || r.nextInt(100) <= successRate) {
            map.moveActor(actor, this.getTargetLocation(actor, map));
            result = actor + " jumped to " + this.target.getClass().getSimpleName() + " successfully.";

        } else {
            int fallDamage = this.target.getFallDamage();
            actor.hurt(fallDamage);
            result = actor + "failed to jump to " + this.target.getClass().getSimpleName() + ". Player receives " + fallDamage + " fall damage.";
            if (!actor.isConscious()) {
                map.removeActor(actor);
                result += System.lineSeparator() + "Player died from fall damage.";
            }

        }
        return result;
    }

    public Location getTargetLocation(Actor actor, GameMap map) {

        Location actorLocation = map.locationOf(actor);
        for (Exit exit: actorLocation.getExits()) {
            if (exit.getName() == this.direction) {
                return exit.getDestination();
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + this.target.getClass().getSimpleName() + " at " + direction;
    }
}
