package game.items;

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

import edu.monash.fit2099.engine.items.Item;

public class PowerStar extends Item implements TradableItem{
    public PowerStar() {
        super("Power Star", '*', true);
        this.addToInventory();
    }

    public int getValue() {
        return 0;
    }
}
