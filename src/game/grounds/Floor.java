package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.npcs.Goomba;
import game.npcs.Koopa;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	/**
	 * Check if actor to-enter is an enemy.
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		Boolean canEnter = true;
		if (actor instanceof Goomba || actor instanceof Koopa) {
			canEnter = false;
		}
		return canEnter;
	}
}
