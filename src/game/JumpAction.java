package game;

import java.util.Locale;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

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
        String result = null;


        if (isTall || r.nextInt(100) <= successRate) {
            map.moveActor(actor, getTargetLocation(actor, map));
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
        int x = map.locationOf(actor).x();
        int y = map.locationOf(actor).y();

        if (this.direction == "North") {
            y = y - 1;

        } else if (this.direction == "South") {
            y = y + 1;

        } else if (this.direction == "East") {
            x = x + 1;

        } else if (this.direction == "West") {
            x = x - 1;

        } else if (this.direction == "North-East") {
            x = x + 1;
            y = y - 1;

        } else if (this.direction == "South-East") {
            x = x + 1;
            y = y + 1;

        } else if (this.direction == "North-West") {
            x = x - 1;
            y = y - 1;

        } else if (this.direction == "South-West") {
            x = x - 1;
            y = y + 1;
        }

        return map.at(x, y);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to " + this.target.getClass().getSimpleName() + " at " + direction;
    }
}
