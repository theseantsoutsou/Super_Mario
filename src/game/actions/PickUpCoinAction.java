package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Coin;
import game.items.Wallet;

/**
 * The PickUpCoinAction class is a special Action that specifically picks up coins only.
 * The PickUpCoinAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class PickUpCoinAction extends Action {
    //Private Attribute
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
     * Modify the credits in the static instance of Wallet
     * Remove the coin from the location
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
     * @return a String to add to the actor's menu of options
     * @see Action#menuDescription(Actor)
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + item;
    }
}