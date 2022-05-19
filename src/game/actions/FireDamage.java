package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * do a check if the ground has a status "mario" if yes, FIRE_ATTACK == no damage
 * if no, damage should be done to character regardless
 */
public class FireDamage {

    public FireDamage(Location location){
        Actor actor = location.getActor();
        String prompt;

        if (location.getGround().hasCapability(Status.MARIO) && actor.hasCapability(Status.MARIO)){
            actor.hurt(0);
        }else{
            actor.hurt(20);
            prompt = actor + " was hurt by fire and lost " + 20 + " hit points.";

            if (!actor.isConscious()){
                GameMap map = location.map();
                map.removeActor(actor);
                prompt = actor + " was killed by fire.";
            }

            System.out.println(prompt);
        }



//        if (!actor.hasCapability(Status.FIRE_ATTACK)){
//            actor.hurt(20);
//            prompt = actor + " was hurt by fire and lost " + 20 + " hit points.";
//
//            if (!actor.isConscious()){
//                GameMap map = location.map();
//                map.removeActor(actor);
//                prompt = actor + " was killed by fire.";
//            }
//
//            System.out.println(prompt);
//
//        }

    }
}
