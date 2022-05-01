package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;

/**
 * A class that figures out a MoveAction that will move the actor one step closer to a target Actor.
 *
 * @author FIT2099 Teaching Team
 * @see edu.monash.fit2099.demo.mars.Application
 */
public class FollowBehaviour implements Behaviour {
	//Private attribute
	private final Actor target;

	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
	 * Secondary constructor for the FollowBehaviour class.
	 * Used to instantiate a FollowBehaviour when the target is not yet determined.
	 *
	 * @see game.npcs.Goomba
	 * @see game.npcs.Koopa
	 */
	public FollowBehaviour() {
		this.target = null;
	}

	/**
	 * Creates a MoveActorAction that moves actor closer to a target.
	 *
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return an MoveActorAction if the destination permits entry; null otherwise
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (this.target == null) {
			return null;
		}
		if(!map.contains(target) || !map.contains(actor))
			return null;
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}