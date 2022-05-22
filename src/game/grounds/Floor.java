package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * The Floor class is a class that represents the floor inside a building.
 * The Floor class is a subclass of the Ground class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Floor extends Ground {

	/**
	 * Constructor for the Floor class.
	 * Calls its parent class Ground class's constructor to set display character.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Check if actor to-enter is an enemy.
	 *
	 * @param actor the Actor to check
	 * @return true if actor is a friendly player; false if the actor is an enemy
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		boolean canEnter = true;
		if (!actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			canEnter = false;
		}
		return canEnter;
	}
}
