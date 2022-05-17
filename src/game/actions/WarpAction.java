package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class WarpAction extends MoveActorAction {

    public WarpAction(Location moveToLocation) {
        super(moveToLocation, "other map");
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + direction;
    }
}
