package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class RescueAction extends Action {
    /**
     * The actor to be saved
     */
    private Actor target;

    /**
     * The direction pointing to the target.
     */
    private String direction;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public RescueAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        this.target.removeCapability(Status.HOSTAGE);
        map.removeActor(actor);
        return "Mario successfully rescued Peach!";
    }

    /**
     * Display description of the target the actor can rescue and in what direction.
     *
     * @param actor The actor performing the action.
     * @return a String to add to actor's menu of options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rescues " + this.target + " at " + this.direction;
    }
}
