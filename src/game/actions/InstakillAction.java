package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * Extension of AttackAction class which allows for attacks that instantly defeat another Actor
 * @author Sean Tsou
 * @since 18/05/22
 */
public class InstakillAction extends AttackAction {
    /**
     * Constructor
     * @param actor Actor to attack
     * @param direction Direction of the attack
     */
    public InstakillAction(Actor actor, String direction) {
        super(actor, direction);
    }

    /**
     * Execute the InstaKillAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.getTarget().hasCapability(Status.CAN_SLEEP) || this.getTarget().hasCapability(Status.ENDGAME)) {
            this.dropLoot(actor, map);
        }
        map.removeActor(this.getTarget());
        return this.getTarget() + " is instakilled.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " instakills " + this.getTarget() + " at " + this.getDirection();
    }
}
