package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Wrench extends WeaponItem implements TradableItem{
    Wrench(String name, char displayChar, int damage, String verb, int hitRate){
        super(name,displayChar, damage, verb, hitRate);
        this.addToInventory();
        this.addCapability(Status.BREAK_SHELL);
    }
    public int getValue(){
        return 200;
    }
}
