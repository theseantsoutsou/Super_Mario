package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

/**
 * The Wrench class is class that represents Mario's weapon.
 * Wrench is the only weapon that can destroy a Koopa's shell.
 * The Wrench class is a subclass of the WeaponItem class and implements the TradableItem interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Wrench extends WeaponItem implements TradableItem{
    //Private attributes
    private int value = 200;

    /**
     * Constructor.
     * Gives the Wrench the capability BREAK_SHELL to break Koopa's shell.
     */
    public Wrench(){
        super("Wrench",'=', 50, "whacks", 80);
        this.addToInventory();
        this.addCapability(Status.BREAK_SHELL);
    }

    /**
     * Interface method - getter for Wrench's value.
     */
    public int getValue(){
        return this.value;
    }
}
