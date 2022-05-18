package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

public class ConsumeWaterAction extends Action {
    Bottle item;
    /**
     * Constructor for the ConsumeAction class.
     * Stores {@code item, this} to ConsumableItemManger to keep track of whether the item can be consumed.
     *
     * @param item the item to be consumed
     */
    public ConsumeWaterAction(Bottle item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String water = this.item.drink(actor);
        return actor + " consumed " + water;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.item;
    }
}
