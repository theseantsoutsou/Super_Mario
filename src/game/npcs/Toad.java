package game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.SpeakAction;
import game.actions.TradeAction;
import game.items.ConsumableItemManager;
import game.items.PowerStar;
import game.items.TradableItemManager;

/**
 * The Toad class is a class that represents the non-playable character Toad in Super Mario.
 * The Toad class is subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Toad extends Actor {

    /**
     * Constructor for the Toad class.
     * Calls its parent class Actor class's constructor to set name, display character, and HP attributes.
     * Add instances of tradable items to Toad's inventory upon instantiation
     */
    public Toad() {
        super("Toad", 'O', 50);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to Toad
     * If the otherActor has the ability to trade and converse, Toad will allow the actor to trade or talk with him
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     * @see Status
     * @see TradeAction
     * @see SpeakAction
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        TradableItemManager.getInstance().replenishInventory(this);
        if (otherActor.hasCapability(Status.TRADE)) {
            actions.add(TradeAction.getTradeActions(this));
        }
        if (otherActor.hasCapability(Status.CONVERSES)) {
            actions.add(new SpeakAction(this));
        }
        return actions;
    }

    /**
     * Toad does not do anything himself.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return a new DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
