package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Dirt;

public class FlattenAction extends Action {
    Dirt dirt;
    String target;
    @Override
    public String execute(Actor actor, GameMap map) {
        Location targeti = map.locationOf(actor);
        map.moveActor(actor, targeti);
        target = String.valueOf(targeti);

        targeti.setGround(dirt);

        menuDescription(actor);

        //create coin ite here

        return target;

    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player has flattened " + target + "and has moved to new location";
    }




}
