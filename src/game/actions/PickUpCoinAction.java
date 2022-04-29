package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Coin;
import game.items.Wallet;

/**
 * Action to allow items to be picked up.
 */
public class PickUpCoinAction extends Action {

    private final Coin item;

    /**
     * Constructor.
     *
     * @param item the coin to pick up
     */
    public PickUpCoinAction(Coin item) {
        this.item = item;
    }

    /**
     * Add the item to the actor's inventory.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a suitable description to display in the UI
     * @see Action#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Wallet.getInstance().modifyCredits(this.item.getValue());
        map.locationOf(actor).removeItem(item);
        return menuDescription(actor);
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string, "Player picks up the coin"
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + item;
    }
}