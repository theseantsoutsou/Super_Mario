package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

public class Wrench extends WeaponItem implements TradableItem{

    private int value = 200;

    public Wrench(){
        super("Wrench",'=', 50, "whacks", 80);
        this.addToInventory();
        this.addCapability(Status.BREAK_SHELL);
    }
    public int getValue(){
        return this.value;
    }
}
