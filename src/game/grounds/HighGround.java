package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.FlattenAction;
import game.actions.JumpAction;

public abstract class HighGround extends Ground {
    private int jumpSuccessRate;
    private int fallDamage;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar, int jumpSuccessRate, int fallDamage) {
        super(displayChar);
        this.jumpSuccessRate = jumpSuccessRate;
        this.fallDamage = fallDamage;
        this.addCapability(Status.HIGH_GROUND);
    }
    /**
     * Getter method for the static variable JUMP_SUCCESS_RATE.
     *
     * @return the success rate of jumping onto a Sprout object
     */
    public int getSuccessRate() {
        return this.jumpSuccessRate;
    }

    /**
     * Getter method for the static variable FALL_DAMAGE.
     *
     * @return the fall damage from the high grouh
     */
    public int getFallDamage() {
        return this.fallDamage;
    }

    /**
     * Actors cannot move to high-grounds unconditionally.
     *
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.FLY);
    }

    /**
     * Returns an ActionList which content depends on the actor's capabilities.
     * Potentially allows an actor to jump onto the high ground or flatten it.
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
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        boolean sameGround = location.map().locationOf(actor).equals(location);

        if (!sameGround) {
            if (actor.hasCapability(Status.POWER_STAR)) {
                actions.add(new FlattenAction(this, direction));
            } else if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                actions.add(new JumpAction(this, direction));
            }
        }
        return actions;
    }
}
