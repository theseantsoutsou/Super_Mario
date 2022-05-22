package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Goomba;

import java.util.Random;

/**
 * The Sprout class is a class that represents the first stage of a tree's life cycle.
 * The Sprout class is a subclass of the Tree class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 02-May-2022
 */
public class Sprout extends Tree {
    /**
     * Constructor for the Sprout class.
     * Calls its parent class's constructor to set display character, jump success rate, and fall damage
     */
    public Sprout() {
        super('+',90, 10);
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, check Sprout's age; grows into Sapling after 10 rounds and gains the status CAN_GROW
     * Tries to spawn a Goomba (and FireFlower if its age == 10).
     * Increments age by calling the parent class's tick function.
     *
     * @param location The location of the Sapling
     * @see Status#CAN_GROW
     */
    @Override
    public void tick(Location location) {
        if (this.getAge() == 10) {
            location.setGround(new Sapling());
            this.addCapability(Status.CAN_GROW);
        }
        this.spawn(location);
        super.tick(location);
    }

    /**
     * Interface method - Sprout has a 10% chance of spawning a Goomba on its location if an actor is not on it.
     * Calls the parent class's spawn function to try and spawn a FireFlower if conditions are met.
     *
     * @param location the location of the Sprout
     * @see Goomba
     */
    @Override
    public void spawn(Location location) {
        Random r = new Random();
        if (this.getAge() < 10 && r.nextInt(100) <= 10 && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
        super.spawn(location);
    }


}
