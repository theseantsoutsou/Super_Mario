package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.SpeechAction;
import game.actions.ToadSpeechAction;
import game.actions.TradeAction;
import game.behaviours.Behaviour;
import game.behaviours.SpeechBehaviour;
import game.items.TradableItemManager;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Toad class is a class that represents the non-playable character Toad in Super Mario.
 * The Toad class is subclass of the Actor class and implements the Speaks interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Toad extends Actor implements Speaks {
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private ArrayList<String> monologues = new ArrayList<>();
    private int turns = -1;

    /**
     * Constructor for the Toad class.
     * Calls its parent class's constructor to set name, display character, and HP attributes.
     * Adds Speech behaviour to the object's {@code behaviours} attribute.
     */
    public Toad() {
        super("Toad", 'O', 50);
        this.behaviours.put(1, new SpeechBehaviour(this));
        this.registerSpeech();
        this.addToMonologues();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to Toad
     * If the otherActor has the ability to TRADe and CONVERSE, Toad will allow the actor to trade or talk with him
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     * @see Status
     * @see TradeAction
     * @see SpeechAction
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        TradableItemManager.getInstance().replenishInventory(this);
        if (otherActor.hasCapability(Status.TRADE)) {
            actions.add(TradeAction.getTradeActions(this));
        }
        if (otherActor.hasCapability(Status.CONVERSES)) {
            actions.add(new ToadSpeechAction(this));
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
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Interface method - Getter for the Toad's monologues
     *
     * @return a list of monologues
     */
    @Override
    public ArrayList<String> getMonologues() {
        return this.monologues;
    }

    /**
     * Interface method - stores Toad's monologues
     */
    @Override
    public void addToMonologues() {
        this.monologues.add("You might need a wrench to smash Koopa's hard shells.");
        this.monologues.add("You better get back to finding the Power Stars.");
        this.monologues.add("The Princess is depending on you! You are our only hope.");
        this.monologues.add("Being imprisoned in these walls can drive a fungus crazy :(");
    }

}
