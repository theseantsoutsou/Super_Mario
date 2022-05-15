package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.npcs.PiranhaPlant;

public class WarpPipe extends Ground implements Jumpable{
    //Private Attributes
    private static final int JUMP_SUCCESS_RATE = 100;
    private static final int FALL_DAMAGE = 0;
    private int age;

    /**
     * Constructor.
     */
    public WarpPipe() {
        super('C');
        this.addCapability(Status.HIGH_GROUND);
        this.age = 0;
    }

    /**
     * Getter method for the static variable JUMP_SUCCESS_RATE.
     *
     * @return the success rate of jumping onto a WarpPipe object
     */
    @Override
    public int getSuccessRate() {
        return JUMP_SUCCESS_RATE;
    }

    /**
     * Getter method for the static variable FALL_DAMAGE.
     *
     * @return the fall damage from a WarpPipe object
     */
    @Override
    public int getFallDamage() {
        return FALL_DAMAGE;
    }

    /**
     * Ground can also experience the joy of time.
     * Warp Pipes will 'spawn' a piranha plant on the 2nd turn,
     *
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        this.age += 1;
        if (this.age == 1) {
            location.addActor(new PiranhaPlant());
            this.addCapability(Status.PIRANHA);
        }
        if (!location.containsAnActor()) {
            this.removeCapability(Status.PIRANHA);
        }
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

    /**
     * WarpPipes can block thrown objects
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Returns an ActionList which content depends on the actor's capabilities.
     * Allows an actor to jump onto the WarpPipe or warp through it.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new collection of Actions
     * @see Status
     * @see JumpAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        boolean sameGround = location.map().locationOf(actor).equals(location);

        if (!this.hasCapability(Status.PIRANHA)) {
            if (!sameGround) {
                actions.add(new JumpAction(this, direction));
            }
        }

        return actions;
    }
}
