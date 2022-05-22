package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FillAction;
import game.actions.JumpAction;
import game.items.Bottle;
import game.items.Water;

/**
 * The Fountain class is an abstract class that represents Magical Fountains.
 * The Fountain class is a subclass of the HighGround class
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 18-May-2022
 * @see HighGround
 */
public abstract class Fountain extends HighGround{
    /**
     * Constructor for the Fountain class.
     * Calls its parent class's constructor to set display character, jump success rate, and fall damage.
     *
     * @param displayChar     character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar, 100, 0);
    }

    /**
     * Allows another actor to jump on to fountain in order to fill their bottle up with the water at this fountain
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a collection of allowable Actions
     */
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

    /**
     * Abstract getter method for the special water at this fountain.
     *
     * @return a Water object
     */
    public abstract Water getWater();
}