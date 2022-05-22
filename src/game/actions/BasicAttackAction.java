package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

/**
 * The BasicAttackAction extends the abstract AttackAction class. It implements simplified logic
 * for attack another actor.
 * The AttackAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class BasicAttackAction extends AttackAction {
	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public BasicAttackAction(Actor target, String direction) {
		super(target, direction);
	}

	@Override
	public void implementAttack(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();

		int damage = weapon.damage();

		this.getTarget().hurt(damage);
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
