package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FlattenAction;
import game.actions.JumpAction;
import game.Status;
import game.items.Coin;

import java.util.Random;

/**
 * Class for a Sapling object
 */
public class Sapling extends Ground implements Jumpable {

    //Private Attributes
    private int age;
    private static final int JUMP_SUCCESS_RATE = 80;
    private static final int FALL_DAMAGE = 20;

    /**
     * Constructor.
     *
     */
    public Sapling() {
        super('t');
        this.age = 0;
    }

    public int getSuccessRate() {
        return JUMP_SUCCESS_RATE;
    }

    public int getFallDamage() {
        return FALL_DAMAGE;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Sapling's Age and tries to spawn a $20 coin.
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        this.age++;
        if (this.age == 10) {
            location.setGround(new Tree());
        } else {
            this.spawn(location);
        }
    }

    /**
     * Randomly adds a sapling onto the map if dice roll succeeds
     * @param location
     */
    public void spawn(Location location) {
        Random r = new Random();
        if (r.nextInt(100) <= 10) {
            location.addItem(new Coin(location));
        }
    }

    /**
     * Allows the an actor to jump onto it and traverse it when crossing between two objects of the
     * same type (sapling to sapling)
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        Boolean sameGround = location.map().locationOf(actor).equals(location);

        if (actor.hasCapability(Status.POWER_STAR) && !sameGround) {
            actions.add(new FlattenAction(this, direction));
        }
        else if(actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !sameGround) {
            actions.add(new JumpAction(this,direction));
        }

        return actions;
    }
}
