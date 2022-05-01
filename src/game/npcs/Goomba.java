package game.npcs;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.AttackAction;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The Goomba class is a class that represents a Goomba in Super Mario, the little fungus guy.
 * The Goomba class is a subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Goomba extends Actor {
	//Private attributes
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

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
		this.behaviours.put(1, new AttackBehaviour());
		this.behaviours.put(2, new FollowBehaviour());
		this.behaviours.put(3, new WanderBehaviour());
	}

	/**
	 * Returns a new collection of the Actions that the otherActor can do Goomba.
	 * If the otherActor is hostile to Goomba, allow the otherActor to attack
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 * @see AttackAction
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();

		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this, direction));
		}

		return actions;
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

		if (this.hasCapability(Status.ATTACKED) || this.hasCapability(Status.GOT_ATTACKED)) {
			Location here = map.locationOf(this);
			for(Exit exit: here.getExits()) {
				Actor target = exit.getDestination().getActor();
				if (target != null && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
					this.behaviours.put(2, new FollowBehaviour(target));
				}
			}
		}

		for (Behaviour behaviour : behaviours.values()) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}

		return new DoNothingAction();
	}

	/**
	 * Creates and returns an intrinsic weapon for Goomba.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {return new IntrinsicWeapon(10, "Kicks" );}




}
