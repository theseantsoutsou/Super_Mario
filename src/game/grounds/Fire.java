package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Fire extends Ground {
    private int age;
    /**
     * constructor
     */
    public Fire() {
        super('v');
        this.age = 1;
    }

//    public Fire (Actor target){
//        super('v');
//        this.target = target;
//        this.age = 1;
//
//    }

    @Override
    public void tick(Location location){
        if (this.age == 5){
            location.setGround(new Dirt());
        }
        if (location.containsAnActor()){
            this.damage(location);

            }
        this.age++;
    }

    public void damage(Location location){
        Actor actor = location.getActor();
        String prompt;
        if (!actor.hasCapability(Status.FIRE_ATTACK)){
            actor.hurt(20);
            prompt = actor + " was hurt by fire and lost " + 20 + " hit points.";
            if (!actor.isConscious()){
                GameMap map = location.map();
                map.removeActor(actor);
                prompt = actor + " was killed by fire.";
            }

            System.out.println(prompt);

        }

    }
}


