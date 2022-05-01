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


public class Sprout extends Ground implements Jumpable {

    //Private Attributes
    private int age;
    private static final int JUMP_SUCCESS_RATE = 90;
    private static final int FALL_DAMAGE = 10;

    /**
     * Constructor.
     *
     */
    public Sprout() {
        super('+');
        this.age = 0;
        this.addCapability(Status.HIGH_GROUND);
    }

    public int getSuccessRate() {
        return JUMP_SUCCESS_RATE;
    }

    public int getFallDamage() {
        return FALL_DAMAGE;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Sprout's Age and tries to spawn a new Goomba.
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
     * Each sprout has a 10% chance of spawning a Goomba on its location if an actor is not on it.
     * @param location The location of the Sprout
     */
    public Boolean spawn(Location location) {
        Boolean spawned = false;
        Random r = new Random();
        if (r.nextInt(100) <= 10 && !location.containsAnActor()) {
            location.addActor(new Goomba());
            spawned = true;
        }
        return spawned;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        Boolean sameGround = location.map().locationOf(actor).equals(location);
        if (actor.hasCapability(Status.POWER_STAR) && !this.spawn(location) && !sameGround) {
            actions.add(new FlattenAction(this, direction));
        }
        else if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.spawn(location) && !sameGround) {
            actions.add(new JumpAction(this, direction));
        }

        return actions;
    }

    /**
     * Check if actor to-enter is an enemy.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
