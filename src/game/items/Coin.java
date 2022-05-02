package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.ResetManager;
import game.Resettable;
import game.actions.PickUpCoinAction;
import game.actions.ResetAction;

import java.util.Random;

/**
 * Coin object
 * Currency that allows the user to trade with Toad if they have enough coins
 */
public class Coin extends Item implements TradableItem, Resettable {
    private Location location;

    /**
     * Constructor
     * @param location
     */
    public Coin(Location location) {
        super("Coin", '$', false);
        //this.addToInventory();
        this.location = location;
        this.addAction(new PickUpCoinAction(this));
        this.registerInstance();
    }

    /**
     * Coins have a random value assigned to them
     * @return
     */
    public int getValue() {
        int randVal = new Random().nextInt(4);
        switch (randVal) {
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
        return null;
    }
    @Override
    public void resetInstance(){
        this.location.removeItem(this);
    }
}