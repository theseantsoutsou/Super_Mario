package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Sapling extends Ground {

    //Private Attributes
    private int age;

    /**
     * Constructor.
     *
     */
    public Sapling() {
        super('t');
        this.age = 0;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Sapling's Age and tries to spawn a $20 coin.
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        this.age++;
        if (this.age == 10) {
            location.setGround(new Tree());
        } //else {
            //this.spawn(location);
        //}
    }

    public void spawn(Location location) {
        Random r = new Random();
        if (r.nextInt(100) <= 10) {
            //location.addActor(new Coin());
        }
    }

}
