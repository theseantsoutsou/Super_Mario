package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * Wrench class, an weapon that the player can trade for with toad
 * The wrench is an item that the player stores in their inventory
 * has the capability of BREAK_SHELL which is the only way the player can destroy a Koopa Shell
 */
public class Wrench extends WeaponItem implements TradableItem{
    public Wrench(){
        super("Wrench",'=', 50, "whacks", 80);
        this.addToInventory();
        this.addCapability(Status.BREAK_SHELL);
    }

    /**
     * How many coins the player needs in order to buy a wrench from Toad
     * @return
     */
    public int getValue(){
        return 200;
    }
}
