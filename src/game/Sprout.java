package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;


public class Sprout extends Ground {

    //Private Attributes
    private int age;

    /**
     * Constructor.
     *
     */
    public Sprout() {
        super('+');
        this.age = 0;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Sprout's Age and tries to spawn a new Goomba.
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        this.age++;
        if (this.age == 10) {
            location.setGround(new Sapling());
        } else {
            this.spawn(location);
        }
    }

    /**
     * Each sprout has a 10% chance of spawning a Goomba on its location if an actor is not on it.
     * @param location The location of the Sprout
     */
    public void spawn(Location location) {
        Random r = new Random();
        if (r.nextInt(100) <= 10 && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
    }

}
