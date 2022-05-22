package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.SuicideAction;
import game.behaviours.*;

import java.util.Random;

/**
 * The Goomba class is a class that represents the enemy character Goomba in Super Mario, the little fungus guy.
 * The Goomba class is a subclass of the Enemy class and implements the Resettable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 02-May-2022
 * @see Enemy
 * @see Resettable
 */
public class Goomba extends Enemy implements Resettable {
	/**
	 * Constructor for the Goomba class.
	 * Calls its parent class's constructor to set all the characteristics of an Enemy character.
	 * Loads an additional WanderBehaviour to its behaviours as it is able to wander around.
	 *
	 * @see WanderBehaviour
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.getBehaviours().put(4, new WanderBehaviour());
	}

	/**
	 * Goomba has a 10 percent chance to commit suicide every turn.
	 * Calls the playTurn function in its parent class to check the valid action based on its behaviours
	 * Goomba will either follow another actor, attack the other actor, or wander around.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the action to be performed during this object's turn.
	 * @see SuicideAction
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		Random r = new Random();
		if (r.nextInt(100) <= 10) {
			return new SuicideAction();
		}
		return super.playTurn(actions, lastAction, map, display);
	}

	/**
	 * Creates and returns an intrinsic weapon for Goomba.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {return new IntrinsicWeapon(10, "Kicks" );}

	/**
	 * Interface method - Remove Goomba from the map upon reset.
	 *
	 * @param map The GameMap which this object exists in.
	 */
	@Override
	public void resetInstance(GameMap map){
		map.removeActor(this);
	}

	/**
	 * Interface method - stores Goomba's monologues
	 */
	@Override
	public void addToMonologues() {
		this.getMonologues().add("Mugga mugga!");
		this.getMonologues().add("Ugha ugha... (Never gonna run around and desert you...)");
		this.getMonologues().add("Ooga-Chaka Ooga-Ooga!");
	}

}


