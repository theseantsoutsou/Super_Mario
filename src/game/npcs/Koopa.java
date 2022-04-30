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
import game.actions.EmptyAction;
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
    private Boolean dormant = false;

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(20, new AttackBehaviour());
        this.addItemToInventory(new SuperMushroom());

    }

    public Boolean isDormant() {
        return dormant;
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
            if (!this.isDormant()) {
                actions.add(new BreakAction(this, direction));
            } else {
                for (Item item : otherActor.getInventory()) {
                    if (item.hasCapability(Status.BREAK_SHELL)) {
                        actions.add(new BreakAction(this, direction));
                    }
                }
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
        if (this.isDormant()) {
            return new EmptyAction();
        }

        if (!this.isConscious()) {
            this.makeDormant();
            return new EmptyAction();

        } else if (lastAction instanceof AttackAction || this.hasCapability(Status.GOT_ATTACKED)) {
            Location here = map.locationOf(this);
            for(Exit exit: here.getExits()) {
                Actor target = exit.getDestination().getActor();
                if (target != null) {
                    this.behaviours.put(10, new FollowBehaviour(target));
                }
            }
        }

        for(Behaviour Behaviour : behaviours.values()) {
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

    public void makeDormant() {
        this.dormant = true;
        this.setDisplayChar('D');
        this.resetMaxHp(50);
        this.behaviours.clear();
    }

}
