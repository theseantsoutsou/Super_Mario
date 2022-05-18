package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.grounds.Fire;

import java.util.Random;

/**
 * Notes:
 * should fire attack be a 'weapon' or an attack by itself
 *
 */


public class FireAttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    public FireAttackAction(Actor target, String direction){
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor.hasCapability(Status.FIRE_ATTACK)){
            map.locationOf(target).setGround(new Fire());
        }

        if (map.locationOf(target).getGround().equals('v')){
            target.hurt(100);
        }

        return "fire";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
