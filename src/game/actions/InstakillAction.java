package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class InstakillAction extends AttackAction {

    public InstakillAction(Actor actor, String direction) {
        super(actor, direction);
    }

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
