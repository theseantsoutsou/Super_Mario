package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
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
    private int value;

    /**
     * A coin is an item which holds value that the player can use to trade.
     * Constructor
     * @param location
     * @param value
     */
    public Coin(Location location, int value) {
        super("Coin", '$', false);
        this.addAction(new PickUpCoinAction(this));
        this.registerInstance();
        this.location = location;
        this.value = value;
    }
    /**
     * Coins have a random value assigned to them
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * Coin class should not use the PickUpItemAction
     * as coins should not exist in the inventory.
     * Overridden version returns null.
     * @param actor
     * @return null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }
    @Override
    /**
     * Resettable interface method - remove coin from map when game is reset.
     **/
    public void resetInstance(GameMap map){
        this.location.removeItem(this);
    }
}