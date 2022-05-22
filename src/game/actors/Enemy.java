package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.SpeechBehaviour;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Enemy class is an abstract class that represents the enemy characters in Super Mario.
 * The Enemy class is subclass of the Actor class and implements the Speaks interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 19-May-2022
 */
public abstract class Enemy extends Actor implements Speaks{
    //Private attribute
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour
    private ArrayList<String> monologues = new ArrayList<>();

    /**
     * Constructor for the Enemy class.
     * Calls on its parent class's constructor to register its {@code name}, {@code displayChar}, and {@code hitPoints}
     * Adds new Attack and Speech behaviours to the object's {@code behaviours} attribute.
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(3, new SpeechBehaviour(this));
        this.registerSpeech();
        this.addToMonologues();
    }

    /**
     * Returns the allowable actions the otherActor can do this Enemy object based on their capabilities.
     * Checks if the otherActor is HOSTILE_TO_ENEMY and if THIS object is not ROOTED, make it follow the otherActor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of allowable Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(this.priorityAllowedAttack(otherActor, direction));

            if (!this.hasCapability(Status.ROOTED)) {
                this.behaviours.put(2, new FollowBehaviour(otherActor));
            }
        }

        return actions;
    }

    /**
     * Select and return an action to perform on the current turn based on its behaviours
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
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @return an {@code AttackAction} based on the otherActor's capabilities
     */
    public AttackAction priorityAllowedAttack(Actor otherActor, String direction) {
        AttackAction action = null;

        if (!this.hasCapability(Status.DORMANT)) {
            if (otherActor.hasCapability(Status.POWER_STAR)) {
                action = new InstakillAction(this, direction);
            } else if (otherActor.hasCapability(Status.FIRE_ATTACK)) {
                action = new FireAttackAction(this, direction);
            } else {
                action = new BasicAttackAction(this, direction);
            }
        }
        else if (otherActor.hasCapability(Status.BREAK_SHELL) || otherActor.hasCapability(Status.POWER_STAR)) {
            action = new BreakAction(this, direction);
        }

        return action;
    }

    /**
     * Getter for the {@code behaviours} attribute.
     *
     * @return the {@code behaviours} attribute containing the Enemy's behaviours
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

    /**
     * Interface method - Getter for the Enemy object's monologues
     *
     * @return a list of monologues
     */
    @Override
    public ArrayList<String> getMonologues(){
        return this.monologues;
    }
}
