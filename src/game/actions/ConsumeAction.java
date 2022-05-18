package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;
import game.items.ConsumableItemManager;
import game.items.TradableItemManager;

/**
 * The ConsumeAction class is a special Action for using Magical Items.
 * The ConsumeAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class ConsumeAction extends Action {
    //Private attributes
    private Consumable item;

    /**
     * Constructor for the ConsumeAction class.
     * Stores {@code item, this} to ConsumableItemManger to keep track of whether the item can be consumed.
     *
     * @param item  the item to be consumed
     *
     * @see ConsumableItemManager
     */
    public ConsumeAction (Consumable item) {
        this.item = item;
    }

    /**
     * Remove the item from the actor's inventory and from the static instance of TradableItemInventory.
     * Gives the actor the associated capability/status.
     * Checks the capability of the item provides and buff the actor accordingly.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a String describing who consumed what
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Item consumedItem = actor.getInventory().get(actor.getInventory().indexOf(this.item));
        TradableItemManager.getInstance().getTradableItems().remove(this.item);
        actor.removeItemFromInventory(consumedItem);
        this.item.applyEffects(actor);
        return actor + " consumed a " + this.item;
    }

    /**
     * Display description of what item the player can consume
     *
     * @param actor The actor performing the action.
     * @return a String to add to actor's menu of options
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.item;
    }
}
