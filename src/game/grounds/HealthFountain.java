package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FillAction;
import game.actions.JumpAction;

/*
public class Fountain extends HighGround {
    public Fountain(){
        super('H', 100, 0);
    }

    @Override

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        boolean sameGround = location.map().locationOf(actor).equals(location);

        if (!sameGround) {
            actions.add(new JumpAction(this, direction));
        }
        else {
            actions.add(new FillAction("Healing Water"));
        }
        return actions;
    }
}
*/