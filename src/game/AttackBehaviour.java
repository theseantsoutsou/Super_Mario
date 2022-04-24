package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class AttackBehaviour implements Behaviour{

    public final Actor target;

    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }


    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {

    /**
        ActionList actions = new ActionList();
        Location targetLoc = map.locationOf(target);
        String there = String.valueOf(targetLoc);

        if (map.contains(target)){
        //Implementation of attack action (goomba -> player)
            //if(target.hasCapability(Status.HOSTILE_TO_ENEMY)){
                //IntrinsicWeapon punch = new IntrinsicWeapon(10, "punches");
                //actions.add(new AttackAction(target, there));



            }



        return actions.get(0);
     */
    return null;
    }


}
