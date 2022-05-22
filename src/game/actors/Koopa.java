package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.behaviours.*;
import game.items.SuperMushroom;

import java.util.ArrayList;

/**
 * The Koopa class is a class that represents the enemy character Koopa in Super Mario, the turtle guy.
 * The Koopa class is a subclass of the Enemy class and implements the Resettable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 02-May-2022
 * @see Enemy
 * @see Resettable
 */
public class Koopa extends Enemy implements Resettable {

    private ArrayList<String> monologues = new ArrayList<>();
    /**
     * Constructor for the Koopa class.
     * Calls its parent class's constructor to set all the characteristics of an Enemy character.
     * Loads an additional WanderBehaviour to its behaviours as it is able to wander around.
     * Adds a CAN_SLEEP status to its capabilities and a SuperMushroom to its inventory
     *
     * @see Status#CAN_SLEEP
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.getBehaviours().put(4, new WanderBehaviour());
        this.addCapability(Status.CAN_SLEEP);
        this.addItemToInventory(new SuperMushroom());
        this.registerInstance();
    }

    /**
     * Secondary constructor used in Koopa's subclasses.
     *
     * @param name        the name of the Koopa
     * @param displayChar the character that will represent the Koopa in the display
     * @param hitPoints   the Koopa's starting hit points
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.getBehaviours().put(4, new WanderBehaviour());
        this.addCapability(Status.CAN_SLEEP);
        this.addItemToInventory(new SuperMushroom());
        this.registerInstance();
    }

    /**
     * If Koopa is unconscious, it is DORMANT, update its display character and other attributes.
     * If Koopa is DORMANT, it cannot do anything.
     * Otherwise, calls the playTurn function in its parent class to check the valid action based on its behaviours
     * Koopa will either follow another actor, attack the other actor, or wander around if it is awake.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the action to be performed during this object's turn.
     * @see Status#DORMANT
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

    /**
     * Interface method - stores Koopa's monologues
     */
    @Override
    public void addToMonologues() {
        this.getMonologues().add("Never gonna make you cry!");
        this.getMonologues().add("Koopi koopi koopii~!");
    }
}
