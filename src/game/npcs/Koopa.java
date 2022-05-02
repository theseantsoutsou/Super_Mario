package game.npcs;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.AttackAction;
import game.actions.BreakAction;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;

/**
 * The Koopa class is a class that represents a Koopa in Super Mario, the turtle guy.
 * The Koopa class is a subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Koopa extends Actor implements Resettable{
    //Private attribute
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

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
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(2, new FollowBehaviour());
        this.behaviours.put(3, new WanderBehaviour());
        this.addCapability(Status.CAN_SLEEP);
        this.addItemToInventory(new SuperMushroom());
        this.registerInstance();

    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to Koopa.
     * If the otherActor is hostile to Koopa, allow the otherActor to attack if Koopa is not DORMANT.
     * If Koopa is DORMANT and the otherActor can break its shell, allow the otherActor to break his shell.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     * @see AttackAction
     * @see BreakAction
     * @see Status#DORMANT
     * @see Status#BREAK_SHELL
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (!this.hasCapability(Status.DORMANT)) {
                actions.add(new AttackAction(this, direction));
            }
            else if (otherActor.hasCapability(Status.BREAK_SHELL)) {
                actions.add(new BreakAction(this, direction));
            }
        }

        return actions;
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
            this.behaviours.clear();
        }

        if (this.hasCapability(Status.DORMANT)) {
            return new DoNothingAction();
        }

        if (this.hasCapability(Status.ATTACKED) || this.hasCapability(Status.GOT_ATTACKED)) {
            Location here = map.locationOf(this);
            for(Exit exit: here.getExits()) {
                Actor target = exit.getDestination().getActor();
                if (target != null && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    this.behaviours.put(2, new FollowBehaviour(target));
                }
            }
        }

        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
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
}
