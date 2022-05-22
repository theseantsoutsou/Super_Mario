package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.FlattenAction;
import game.actions.JumpAction;

/**
 * The HighGround class is an abstract class that represents high grounds.
 * The HighGround class is a subclass of the Ground class
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 17-May-2022
 */
public abstract class HighGround extends Ground {
    //Private attributes
    private final int jumpSuccessRate;
    private final int fallDamage;

    /**
     * Constructor for the HighGround class.
     * Calls its parent class's constructor to set display character.
     * Sets the {@code jumpSuccessRate} and {@code fallDamage} attributes.
     * Adds the status HIGH_GROUND to the object's capability set.
     *
     * @param displayChar       character to display for this type of terrain
     * @param jumpSuccessRate   the success rate of jumping onto this terrain
     * @param fallDamage        the damage taken from falling from this terrain
     * @see Status#HIGH_GROUND
     */
    public HighGround(char displayChar, int jumpSuccessRate, int fallDamage) {
        super(displayChar);
        this.jumpSuccessRate = jumpSuccessRate;
        this.fallDamage = fallDamage;
        this.addCapability(Status.HIGH_GROUND);
    }
    /**
     * Getter method for the jumpSuccessRate attribute.
     *
     * @return the success rate of jumping onto a HighGround object
     */
    public int getSuccessRate() {
        return this.jumpSuccessRate;
    }

    /**
     * Getter method for the fallDamage attribute.
     *
     * @return the fall damage from the HighGround object
     */
    public int getFallDamage() {
        return this.fallDamage;
    }

    /**
     * Actors cannot move to high-grounds unconditionally.
     * Check if the actor has the status FLY to unconditionally move onto high grounds.
     *
     * @param actor the Actor to check
     * @return true if the actor can fly; false otherwise
     * @see Status#FLY
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
