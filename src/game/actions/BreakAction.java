package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * The BreakAction class is a special Action for breaking dormant actor's shell.
 * BreakAction is generated conditionally and therefore does not check Actor's capabilities.
 * The BreakAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class BreakAction extends AttackAction {

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public BreakAction(Actor target, String direction) {
        super(target, direction);
    }

    /**
     * Executes the action of destroying a dormant actor's shell only when the target is in a dormant state.
     * The dormant actor to drop items in its inventory when shell is destroyed.
     * Has a 100 percent hit rate because the dormant target will be stationary.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a String to output to console that describes the result of this action
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        this.dropLoot(actor, map);

        map.removeActor(this.getTarget());

        return actor + " broke " + this.getTarget() + "'s shell" + System.lineSeparator() + this.getTarget() + " is killed";
    }

    /**
     * Display description of the target the actor can break and in what direction.
     *
     * @param actor The actor performing the action.
     * @return a String to add to actor's menu of options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " breaks " + this.getTarget() + "'s shell at " + this.getDirection();

    }
}
