package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;

/**
 * The BreakAction class is a special Action for breaking dormant actor's shell.
 * BreakAction is generated conditionally and therefore does not check Actor's capabilities.
 * The BreakAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class BreakAction extends Action {

    /**
     * The dormant actor whose shell is to be broken
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public BreakAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
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

        String result;

        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : this.target.getInventory())
            dropActions.add(item.getDropAction(actor));
        for (Action drop : dropActions)
            drop.execute(this.target, map);
        // remove actor
        map.removeActor(this.target);
        result = actor + " broke Koopa's shell";
        result += System.lineSeparator() + this.target + " is killed";

        return result;
    }

    /**
     * Display description of the target the actor can break and in what direction.
     *
     * @param actor The actor performing the action.
     * @return a String to add to actor's menu of options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " breaks " + this.target + "'s shell at " + direction;

    }
}
