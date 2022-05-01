package game.npcs;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.AttackAction;
import game.actions.BreakAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;
/**
 * A little fungus guy.
 */
public class Koopa extends Actor {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(2, new FollowBehaviour());
        this.behaviours.put(3, new WanderBehaviour());
        this.addCapability(Status.CAN_SLEEP);
        this.addItemToInventory(new SuperMushroom());

    }

    /**
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (!this.hasCapability(Status.DORMANT)) {
                actions.add(new AttackAction(this, direction));
            }
            else if (otherActor.hasCapability(Status.BREAK_SHELL)) {
                actions.add(new BreakAction(this, direction));
            }
        }

        return actions;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            this.setDisplayChar('D');
            this.resetMaxHp(50);
            this.behaviours.clear();
        }

        if (this.hasCapability(Status.DORMANT)) {
            return new DoNothingAction();
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

        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

}
