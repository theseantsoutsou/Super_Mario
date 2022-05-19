package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public abstract class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Constructor
     * @param target
     * @param direction
     */
    protected AttackAction (Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public abstract String execute(Actor actor, GameMap map);

    @Override
    public abstract String menuDescription(Actor actor);

    public String result(GameMap map) {
        String result = "";
        if (!this.target.isConscious()) {
            if (!this.target.hasCapability(Status.CAN_SLEEP)) {
                // remove actor
                map.removeActor(this.target);
                result = System.lineSeparator() + this.target + " was killed";
            }
            else {
                this.target.addCapability(Status.DORMANT);
                result = System.lineSeparator() + this.target + " went dormant.";
            }
        }
        return result;
    }

    public void dropLoot(Actor actor, GameMap map) {
        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : this.target.getInventory())
            dropActions.add(item.getDropAction(actor));
        for (Action drop : dropActions)
            drop.execute(this.target, map);
    }

    public Actor getTarget() {
        return this.target;
    }

    public String getDirection() {
        return this.direction;
    }
}
