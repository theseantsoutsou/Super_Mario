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
import game.ResetManager;
import game.actions.ResetAction;
import game.grounds.Dirt;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * A little fungus guy.
 */
public class Goomba extends Actor {
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 10);
		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(20, new AttackBehaviour());


	}

	/**
	 * Player can attack Goomba and cause harm to it
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}


		//resetting the game
		if (otherActor.hasCapability(Status.RESETTABLE)){
			map.removeActor(this);
		}

		return actions;
	}

	/**
	 * Figure out what to do next.
	 * During it's turn, the goomba may attack the player if it is close enough to the player
	 * additionally, the goomba has a 10% chance of being removed from the map due to suicide
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		Random r = new Random();
		if (r.nextInt(100) <= 10) {
			return new SuicideAction();
		}

		if (lastAction instanceof AttackAction || this.hasCapability(Status.GOT_ATTACKED)) {
			Location here = map.locationOf(this);
			for(Exit exit: here.getExits()) {
				Actor target = exit.getDestination().getActor();
				if (target != null) {
					this.behaviours.put(10, new FollowBehaviour(target));
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
	 * What a goomba attacks the player with
	 * @return
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {return new IntrinsicWeapon(10, "Kicks" );}




}
