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
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(20, new AttackBehaviour());

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
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));

        //wrench for complete destruction
        if (this.getDisplayChar() == 'D'){
            boolean wrenchCheck;
            if (otherActor.getInventory().contains("Wrench")){
                wrenchCheck = true;
            }else {wrenchCheck = false;}
            if (wrenchCheck){
                actions.add(new AttackAction(this, direction));
            }
            if (!this.isConscious()){
                SuperMushroom mush = new SuperMushroom();
                mush.getDropAction(this);
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
        if (!this.isConscious()) {
            setDisplayChar('D');
            this.resetMaxHp(50);
            this.behaviours.clear();
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

}
