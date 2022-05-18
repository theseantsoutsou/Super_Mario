package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FillAction;
import game.actions.JumpAction;
import game.items.Bottle;
import game.items.Water;

public abstract class Fountain extends HighGround{
    /**
     * Constructor.
     *
     * @param displayChar     character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar, 100, 0);
    }

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        boolean sameGround = location.map().locationOf(actor).equals(location);

        if (!sameGround) {
            actions.add(new JumpAction(this, direction));
        }
        else {
            for(Item i:actor.getInventory()){
                if (i instanceof Bottle){
                    actions.add(new FillAction(this.getWater(), (Bottle)i));
                }
            }
        }
        return actions;
    }

    public abstract Water getWater();
}