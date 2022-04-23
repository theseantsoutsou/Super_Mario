package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

/***
 * PowerStar Attributes:
 * can be picked up
 * can be traded
 * is in the game for 10 turns (ground and player inventory, resets upon pickup)
 * heals 200 hit points
 * takes no damage
 * invincibility lasts for 10 turns upon activation
 */
public class PowerStar extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public PowerStar(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }


}
