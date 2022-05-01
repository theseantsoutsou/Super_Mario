package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String result = null;
		Weapon weapon = actor.getWeapon();

		actor.addCapability(Status.ATTACKED);
		this.target.addCapability(Status.GOT_ATTACKED);

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {

			return actor + " misses " + target + ".";
		}

		if (this.target.hasCapability(Status.POWER_STAR)) {
			return this.target + " is invincible! " + this.target + " takes no damage!";
		}

		if (actor.hasCapability(Status.POWER_STAR)) {
			if (!this.target.hasCapability(Status.CAN_SLEEP)) {
				map.removeActor(this.target);
			}
			else {
				new BreakAction(this.target, this.direction).execute(actor, map);
			}
			return this.target + " is instakilled.";
		}

		int damage = weapon.damage();

		this.target.hurt(damage);

		result = actor + " " + weapon.verb() + " " + this.target + " for " + damage + " damage.";

		if (!this.target.isConscious()) {
			if (!this.target.hasCapability(Status.CAN_SLEEP)) {
				// remove actor
				map.removeActor(this.target);
				result += System.lineSeparator() + this.target + " is killed.";
			}
			else {
				this.target.addCapability(Status.DORMANT);
				result += System.lineSeparator() + this.target + " went dormant.";
			}
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + this.target + " at " + direction;
	}
}
