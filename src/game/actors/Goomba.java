package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.SuicideAction;
import game.behaviours.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Goomba class is a class that represents a Goomba in Super Mario, the little fungus guy.
 * The Goomba class is a subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Goomba extends Enemy implements Resettable {
	/**
	 * Constructor for the Goomba class.
	 * Calls its parent class Actor class's constructor to set name, display character, and HP attributes.
	 * Loads new behaviours to its behaviours attribute in order of priority.
	 *
	 * @see FollowBehaviour
	 * @see AttackBehaviour
	 * @see WanderBehaviour
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.getBehaviours().put(3, new WanderBehaviour());
		this.addToMonologues();
	}



	/**
	 * Goomba has a 10 percent chance to commit suicide every turn.
	 * If Goomba is engaged in a fight, it follows the other actor engaged
	 * Goomba will either follow another actor, attack the other actor, or wander around.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return an Action
	 * @see SuicideAction
	 * @see Behaviour
	 * @see	Status#ATTACKED
	 * @see Status#GOT_ATTACKED
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

	@Override
	public void addToMonologues() {
		this.getMonologues().add("Mugga mugga!");
		this.getMonologues().add("Ugha ugha... (Never gonna run around and desert you...)");
		this.getMonologues().add("Ooga-Chaka Ooga-Ooga!");
	}

}


