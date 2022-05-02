package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.actions.PickUpCoinAction;

/**
 * The Coin class is a class that represents the currency in the game.
 * Players can trade with Toad and buy items if they have enough coins.
 * The Coin class is a subclass of Item and implements the TradableItem and Resettable interfaces.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Coin extends Item implements TradableItem, Resettable {
    private Location location;
    private int value;

    /**
     * Constructor
     * A coin is an item which holds value that the player can use to trade.
     * @param location location of the coin
     * @param value value of the coin
     */
    public Coin(Location location, int value) {
        super("Coin", '$', false);
        this.addAction(new PickUpCoinAction(this));
        this.registerInstance();
        this.location = location;
        this.value = value;
    }

    /**
     * Getter
     *
     * TradableItem interface method - get the value of the Coin.
     */
    public int getValue() {
        return value;
    }

    /**
     * Coin class should not use the PickUpItemAction as coins should not exist in the inventory.
     * Overridden version returns null.
     *
     * @param actor Actor picking up the coin.
     * @return null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }

    /**
     * Resettable interface method - remove coin from map when game is reset.
     * @param map The GameMap which this object exists in.
     */
    @Override
    public void resetInstance(GameMap map){
        this.location.removeItem(this);
    }
}