package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.BasicAttackAction;
import game.actions.BreakAction;
import game.actions.FireAttackAction;
import game.actions.InstakillAction;
import game.behaviours.*;
import game.items.SuperMushroom;

import java.util.ArrayList;

/**
 * The Koopa class is a class that represents a Koopa in Super Mario, the turtle guy.
 * The Koopa class is a subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Koopa extends Enemy implements Resettable, Speaks{

    private ArrayList<String> monologues = new ArrayList<>();
    /**
     * Constructor for the Koopa class.
     * Calls its parent class Actor class's constructor to set name, display character, and HP attributes.
     * Loads new behaviours to its behaviours attribute in order of priority.
     * Adds a CAN_SLEEP status to its capabilities and a SuperMushroom to its inventory
     *
     * @see FollowBehaviour
     * @see AttackBehaviour
     * @see WanderBehaviour
     * @see Status#CAN_SLEEP
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.getBehaviours().put(3, new WanderBehaviour());
        this.addCapability(Status.CAN_SLEEP);
        this.addItemToInventory(new SuperMushroom());
        this.registerInstance();
        this.addToMonologues();
    }

    /**
     * Secondary constructor used in Koopa's subclasses
     * @param name
     * @param displayChar
     * @param hitPoints
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.getBehaviours().put(3, new WanderBehaviour());
        this.addCapability(Status.CAN_SLEEP);
        this.addItemToInventory(new SuperMushroom());
        this.registerInstance();
    }

    /**
     * If Koopa is unconscious, it is DORMANT, update its display character and other attributes.
     * If Koopa is DORMANT, it cannot do anything.
     * If Koopa is engaged in a fight, it follows the other actor engaged
     * Koopa will either follow another actor, attack the other actor, or wander around if it is awake.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an Action
     * @see Behaviour
     * @see Status#DORMANT
     * @see	Status#ATTACKED
     * @see Status#GOT_ATTACKED
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            this.setDisplayChar('D');
            this.resetMaxHp(50);
            this.getBehaviours().clear();
        }
        if (this.hasCapability(Status.DORMANT)) {
            return new DoNothingAction();
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Creates and returns an intrinsic weapon for Koopa.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }


    /**
     * Interface method -  Force Koopa to drop the items (SuperMushroom) in its inventory then remove it from the map.
     *
     * @param map The GameMap which this object exists in.
     */
    @Override
    public void resetInstance(GameMap map){
        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : this.getInventory())
            dropActions.add(item.getDropAction(this));
        for (Action drop : dropActions)
            drop.execute(this, map);
        // remove actor
        map.removeActor(this);
    }

    @Override
    public ArrayList<String> getMonologues() {
        return monologues;
    }

    @Override
    public void addToMonologues() {
        this.monologues.add("Never gonna make you cry!");
        this.monologues.add("Koopi koopi koopii~!");
    }
}
