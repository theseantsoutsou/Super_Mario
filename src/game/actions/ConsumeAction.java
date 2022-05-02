package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.TradableItemManager;
import game.items.TradableItemInventory;

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
    private Item item;
    private Status capability;

    /**
     * Constructor for the ConsumeAction class.
     * Stores {@code item, this} to ConsumableItemManger to keep track of whether the item can be consumed.
     *
     * @param item  the item to be consumed
     * @param capability    the buff that the item will provide
     * @see TradableItemManager
     */
    public ConsumeAction (Item item, Status capability) {
        this.item = item;
        this.capability = capability;
        TradableItemManager.getInstance().addToHashMap(item, this);
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
        actor.removeItemFromInventory(this.item);
        TradableItemInventory.getInstance().getTradableItems().remove(this.item);
        actor.addCapability(capability);
        if (this.capability == Status.TALL) {
            actor.increaseMaxHp(50);
        }
        else if (this.capability == Status.POWER_STAR) {
            actor.heal(200);
        }
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
