package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;

import java.util.Random;

public class Coin extends Item implements TradableItem {
    private Location location;

    public Coin(Location location) {
        super("Coin", '$', false);
        //this.addToInventory();
        this.location = location;
        this.addAction(new PickUpCoinAction(this));
    }

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
}