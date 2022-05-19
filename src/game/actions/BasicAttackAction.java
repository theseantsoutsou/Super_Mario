package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

/**
 * The AttackAction class is a special Action for attacking other Actors.
 * The AttackAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class BasicAttackAction extends AttackAction {

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public BasicAttackAction(Actor target, String direction) {
		super(target, direction);
	}

	/**
	 * Execute the AttackAction.
	 * Retrieves Actor's weapon then calculates the chance to hit opponent.
	 * Checks to see if Power Star is active for either target and attacker and implements the appropriate attack sequence
	 * Checks to see if target is unconscious
	 * If target is unconscious and cannot go to sleep, remove from map.
	 * If target is unconscious and can go to sleep, make target dormant.
	 *
	 * @param actor The actor performing the action
	 * @param map The map the actor is on.
	 * @return a String to output to console that describes the result of this attack
	 * @see BreakAction
	 * @see Status#CAN_SLEEP
	 * @see Status#DORMANT
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		int chance = weapon.chanceToHit();

		if ((rand.nextInt(100) > chance)) {
			return actor + " misses " + this.getTarget() + ".";
		}

		if (this.getTarget().hasCapability(Status.POWER_STAR)) {
			return this.getTarget() + " is invincible! " + this.getTarget() + " takes no damage!";
		}

		int damage = weapon.damage();

		this.getTarget().hurt(damage);

		return actor + " " + weapon.verb() + " " + this.getTarget() + " for " + damage + " damage." + this.result(map);
	}

	/**
	 * Supplies appropriate descriptor for action.
	 *
	 * @param actor The actor performing the action.
	 * @return a String to add to actor's menu of options
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + this.getTarget() + " at " + this.getDirection();
	}
}
