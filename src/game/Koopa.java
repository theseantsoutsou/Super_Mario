package game;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

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

            //dormant when 'killed'
            if (!this.isConscious()){
                setDisplayChar('D');
                otherActor.capabilitiesList().remove(Status.HOSTILE_TO_ENEMY); //note: should this.capabilities be cleared? or does player lose hostile_to_enemy?
                //check if hostile_to_enemy is actor specific
            }

        //Implementation of attack action (goomba -> player)
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            IntrinsicWeapon punch = new IntrinsicWeapon(30, "punches");
            actions.add(new AttackAction(otherActor,direction));


        }

        //wrench for complete destruction
        if (this.getDisplayChar() == 'D'){
            boolean wrenchCheck;
            if (otherActor.getInventory().contains("Wrench")){
                wrenchCheck = true;
            }else {wrenchCheck = false;}
            if (wrenchCheck){
                otherActor.capabilitiesList().add(Status.HOSTILE_TO_ENEMY);
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
        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

}
