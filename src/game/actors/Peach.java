package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.RescueAction;
import game.behaviours.Behaviour;
import game.behaviours.SpeechBehaviour;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Peach class is a class that represents the non-playable character Peach in Super Mario.
 * The Peach class is subclass of the Actor class and implements the Speaks interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 19-May-2022
 * @see Speaks
 */
public class Peach extends Actor implements Speaks{
    //Private attributes
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private ArrayList<String> monologues = new ArrayList<>();

    /**
     * Constructor for the Peach class.
     * Calls on her parent class's constructor to register its {@code name}, {@code displayChar}, and {@code hitPoints}
     * Adds the status of HOSTAGE to her capability set
     * Adds Speech behaviour to the object's {@code behaviours} attribute.
     *
     * @see Status#HOSTAGE
     */
    public Peach() {
        super("Peach", 'P', 100);
        this.addCapability(Status.HOSTAGE);
        this.behaviours.put(1, new SpeechBehaviour(this));
        this.registerSpeech();
        this.addToMonologues();
    }

    /**
     * Returns the allowable actions the otherActor can do Peach based on their capabilities.
     * If otherActor has the status HERO, allow the otherActor to rescue Peach.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of allowable Actions.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HERO)) {
            actions.add(new RescueAction(this, direction));
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn based on Peach's behaviours
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the action to be performed during this object's turn.
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
     * Interface method - getter for Peach's monologues
     * @return a list of monologues
     */
    @Override
    public ArrayList<String> getMonologues() {
        return monologues;
    }

    /**
     * Interface method - stores Peach's monologues
     */
    @Override
    public void addToMonologues() {
        monologues.add("Dear Mario, I'll be waiting for you...");
        monologues.add("Never gonna give you up!");
        monologues.add("Release me, or I will kick you!");
    }
}
