package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.ConsumableItem;
import game.items.TradableItemManager;

public class ConsumeWaterAction extends ConsumeAction{
    Bottle item;
    /**
     * Constructor for the ConsumeAction class.
     * Stores {@code item, this} to ConsumableItemManger to keep track of whether the item can be consumed.
     *
     * @param item the item to be consumed
     */
    public ConsumeWaterAction(Bottle item) {
        super(item);
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        this.item.applyEffects(actor);
        return actor + " consumed a " + this.item;
    }
}
