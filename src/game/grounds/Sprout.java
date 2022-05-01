package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FlattenAction;
import game.actions.JumpAction;
import game.Status;
import game.npcs.Goomba;

import java.util.Random;

/**
 * The Sprout class is a class that represents the first stage of a tree's life cycle.
 * The Sprout class is a subclass of the Ground class and implements the Jumpable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Sprout extends Ground implements Jumpable {

    //Private Attributes
    private int age;
    private static final int JUMP_SUCCESS_RATE = 90;
    private static final int FALL_DAMAGE = 10;

    /**
     * Constructor for the Sprout class.
     * Calls its parent class Ground class's constructor to set display character.
     * Initializes its age to 0 and adds a HIGH_GROUND status to its capability.
     *
     * @see Status#HIGH_GROUND
     */
    public Sprout() {
        super('+');
        this.age = 0;
        this.addCapability(Status.HIGH_GROUND);
    }

    /**
     * Getter method for the static variable JUMP_SUCCESS_RATE.
     *
     * @return the success rate of jumping onto a Sprout object
     */
    @Override
    public int getSuccessRate() {
        return JUMP_SUCCESS_RATE;
    }

    /**
     * Getter method for the static variable FALL_DAMAGE.
     *
     * @return the fall damage from a Sprout object
     */
    @Override
    public int getFallDamage() {
        return FALL_DAMAGE;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Sprout's age; grows into a Sapling after 10 rounds.
     * Tries to spawn a goomba if it does not grow into a Sapling.
     *
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        this.age++;
        if (this.age == 10) {
            location.setGround(new Sapling());
        } else {
            this.spawn(location);
        }
    }

    /**
     * Sprout has a 10% chance of spawning a Goomba on its location if an actor is not on it.
     *
     * @param location the location of the Sprout
     * @return true if a Goomba spawns, false otherwise
     * @see Goomba
     */
    public boolean spawn(Location location) {
        Boolean spawned = false;
        Random r = new Random();
        if (r.nextInt(100) <= 10 && !location.containsAnActor()) {
            location.addActor(new Goomba());
            spawned = true;
        }
        return spawned;
    }

    /**
     * Returns an ActionList which content depends on the actor's capabilities.
     * Potentially allows an actor to jump onto the Sprout or flatten it.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new collection of Actions
     * @see Status
     * @see FlattenAction
     * @see JumpAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        Boolean sameGround = location.map().locationOf(actor).equals(location);

        if (!sameGround && !this.spawn(location)) {
            if (actor.hasCapability(Status.POWER_STAR)) {
                actions.add(new FlattenAction(this, direction));
            }
            else if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                actions.add(new JumpAction(this, direction));
            }
        }

        return actions;
    }

    /**
     * Actors cannot move to high-grounds unconditionally.
     *
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
