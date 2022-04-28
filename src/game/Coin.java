package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

import java.util.Random;

public class Coin extends Item implements TradableItem{
    Coin(String name, char displayChar, boolean portable){
        super(name, displayChar, portable);
    }
    @Override
    public int getValue() {
        int randVal = new Random().nextInt(4);
        switch(randVal){
            case 0:
               return 5;
            case 1:
                return 10;
            case 2:
                return 20;
            case 3:
                return 9001;
        }
        return 0;
    }
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        Wallet.getInstance().modifyCredits(this.getValue());
        return null;
    }
}