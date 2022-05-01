package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FlattenAction;
import game.actions.JumpAction;
import game.Status;

/**
 * The Wall class is a class that represents a generic brick wall.
 * The Wall class is a subclass of the Ground class and implements the Jumpable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Wall extends Ground implements Jumpable {
	//Private Attributes
	private static final int JUMP_SUCCESS_RATE = 80;
	private static final int FALL_DAMAGE = 20;

	/**
	 * Constructor for the Wall class.
	 * Calls its parent class Ground class's constructor to set display character.
	 * Adds a HIGH_GROUND status to its capability.
	 * @see Status#HIGH_GROUND
	 */
	public Wall() {
		super('#');
		this.addCapability(Status.HIGH_GROUND);
	}

	/**
	 * Getter method for the static variable JUMP_SUCCESS_RATE.
	 * @return the success rate of jumping onto a Wall object
	 */
	@Override
	public int getSuccessRate() {
		return JUMP_SUCCESS_RATE;
	}

	/**
	 * Getter method for the static variable FALL_DAMAGE.
	 * @return the fall damage from a Wall object
	 */
	@Override
	public int getFallDamage() {
		return FALL_DAMAGE;
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
	 * Walls can block thrown objects
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * Returns an ActionList which content depends on the actor's capabilities.
	 * Potentially allows an actor to jump onto the Wall or flatten it.
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

		if (!sameGround) {
			if (actor.hasCapability(Status.POWER_STAR)) {
				actions.add(new FlattenAction(this, direction));
			}
			else if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
				actions.add(new JumpAction(this, direction));
			}
		}

		return actions;
	}
}
