package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FlattenAction;
import game.actions.JumpAction;
import game.Status;
import game.npcs.Koopa;

import java.util.List;
import java.util.Random;

/**
 * The Tree class is a class that represents the final stage of a tree's life cycle.
 * The Tree class is a subclass of the Ground class and implements the Jumpable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Tree extends Ground implements Jumpable {

    //Private Attributes
    private int age;
    private static final int JUMP_SUCCESS_RATE = 70;
    private static final int FALL_DAMAGE = 30;

    /**
     * Constructor for the Tree class.
     * Calls its parent class Ground class's constructor to set display character.
     * Initializes its age to 0 and adds a HIGH_GROUND status to its capability.
     * @see Status#HIGH_GROUND
     */
    public Tree() {
        super('T');
        this.age = 0;
        this.addCapability(Status.HIGH_GROUND);
    }

    /**
     * Getter method for the static variable JUMP_SUCCESS_RATE.
     * @return the success rate of jumping onto a Tree object
     */
    @Override
    public int getSuccessRate() {
        return JUMP_SUCCESS_RATE;
    }

    /**
     * Getter method for the static variable FALL_DAMAGE.
     * @return the fall damage from a Tree object
     */
    @Override
    public int getFallDamage() {
        return FALL_DAMAGE;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Tree's age; spawns a new sprout every 5 rounds randomly.
     * Has a 20 percent chance to wither and die every turn
     * If it does not die, it has a 15 percent chance of spawning a Koopa at its location.
     * @param location The location of the Tree
     */
    @Override
    public void tick(Location location) {
        this.age++;
        if (this.age %5 == 0) {
            this.growSprout(location);
        }
        if (!this.die(location)) {
            this.spawn(location);
        }
    }

    /**
     * Trees have a 15 percent chance of spawning a Koopa if an actor is not on it.
     * @param location the location of the Tree
     * @return true if a Koopa has been spawned, false otherwise
     * @see Koopa
     */
    public boolean spawn(Location location) {
        Random r = new Random();
        Boolean spawned = false;

        if (r.nextInt(100) <= 15 && !location.containsAnActor()) {
            location.addActor(new Koopa());
            spawned = true;
        }
        return spawned;
    }

    /**
     * Randomly spawn a sprout at a fertile ground around the Tree.
     * @param location the location of the Tree.
     * @see Status#FERTILE
     */
    public void growSprout(Location location) {
        List<Exit> exits = location.getExits();
        Random r = new Random();
        boolean grown = false;

        while (!grown) {
            int idx = r.nextInt(exits.size());
            if (exits.get(idx).getDestination().getGround().hasCapability(Status.FERTILE)){
                exits.get(idx).getDestination().setGround(new Sprout());
                grown = true;
            }
        }
    }

    /**
     * Trees have a 20 percent chance to wither and die every round.
     * @param location the location of the Tree
     * @return true if the Tree dies, false otherwise
     */
    public boolean die(Location location) {
        Boolean dead = false;
        Random r = new Random();
        if (r.nextInt(100) <= 20) {
            location.setGround(new Dirt());
            dead = true;
        }
        return dead;
    }

    /**
     * Returns an ActionList which content depends on the actor's capabilities.
     * Potentially allows an actor to jump onto the Tree or flatten it.
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
