package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.ResetManager;
import game.actions.FlattenAction;
import game.actions.JumpAction;
import game.Status;
import game.actions.ResetAction;
import game.actions.SuicideAction;
import game.npcs.Koopa;

import java.util.List;
import java.util.Random;

/**
 * Class for a Tree Ground object (high ground, Mature)
 */
public class Tree extends Ground implements Jumpable {

    //Private Attributes
    private int age;
    private static final int JUMP_SUCCESS_RATE = 70;
    private static final int FALL_DAMAGE = 30;

    /**
     * Constructor.
     *
     */
    public Tree() {
        super('T');
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
        if (this.age %5 == 0) {
            this.growSprout(location);
        }
        if (!this.die(location)) {
            this.spawn(location);
        }
    }

    /**
     * Trees have a chance to spawn a Koopa from them (15%)
     * @param location
     * @return
     */
    public Boolean spawn(Location location) {
        Random r = new Random();
        Boolean spawned = false;

        if (r.nextInt(100) <= 15 && !location.containsAnActor()) {
            location.addActor(new Koopa());
            spawned = true;
        }
        return spawned;
    }

    /**
     * Sprouts grow into trees after 5 turns
     * @param location
     */
    public void growSprout(Location location) {
        List<Exit> exits = location.getExits();
        Random r = new Random();
        Boolean grown = false;

        while (!grown) {
            int idx = r.nextInt(exits.size());
            if (exits.get(idx).getDestination().getGround() instanceof Dirt){
                exits.get(idx).getDestination().setGround(new Sprout());
                grown = true;
            }
        }
    }

    /**
     * Trees have a chance of being removed and turning to dirt every turn (20%)
     * @param location
     * @return
     */
    public Boolean die(Location location) {
        Boolean dead = false;
        Random r = new Random();
        if (r.nextInt(100) <= 20) {
            location.setGround(new Dirt());
            dead = true;
        }
        return dead;
    }

    /**
     * Allows the player to jump on to a Tree from lower ground (if dice roll suceeds)
     * Allows the player to traverse between Trees objects
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        Boolean sameGround = location.map().locationOf(actor).equals(location);

        if (actor.hasCapability(Status.POWER_STAR) && !this.spawn(location) && !sameGround) {
            actions.add(new FlattenAction(this, direction));
        }
        else if(actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.spawn(location) && !sameGround) {
            actions.add(new JumpAction(this,direction));
        }

        //resetting the game
        if (actor.hasCapability(Status.RESETTABLE)){
            Random r = new Random();
            if (r.nextInt(100) <= 50) {
                location.setGround(new Dirt());
            }
        }

        return actions;
    }

}
