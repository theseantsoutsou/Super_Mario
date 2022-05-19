package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.grounds.Fire;

import java.util.Random;

public class FireAttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;


    protected Ground ground;

    public FireAttackAction(Actor target, String direction){
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        this.ground = map.locationOf(target).getGround();

        if (actor.hasCapability(Status.MARIO)){
            map.locationOf(target).setGround(new Fire(actor, ground));

        }else{
            map.locationOf(target).setGround(new Fire(ground));
        }

        return "fire";
    }





    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " with fire";
    }
}
