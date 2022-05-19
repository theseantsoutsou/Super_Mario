package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.FireDamage;

public class Fire extends Ground {
    private int age;
    private Ground ground;
    /**
     * constructor
     */
    public Fire(Ground ground) {
        super('v');
        this.age = 1;
        this.ground = ground;
    }

    public Fire(Actor actor, Ground ground){
        super('v');
        this.addCapability(Status.MARIO);
        this.age = 1;
        this.ground = ground;

    }


    @Override
    public void tick(Location location){
        if (this.age == 3){
            location.setGround(ground);
        }
        else if (location.containsAnActor()){
            new FireDamage(location);

            }
        this.age++;
    }


}


