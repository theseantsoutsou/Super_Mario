package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.FireFlower;
import game.npcs.Goomba;

import java.util.Random;

/**
 * The Sprout class is a class that represents the first stage of a tree's life cycle.
 * The Sprout class is a subclass of the Ground class and implements the Jumpable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Sprout extends Tree {
    /**
     * Constructor for the Sprout class.
     * Calls its parent class Ground class's constructor to set display character.
     * Initializes its age to 0 and adds a HIGH_GROUND status to its capability.
     *
     * @see Status#HIGH_GROUND
     */
    public Sprout() {
        super('+',90, 10);

    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Sprout's age; grows into a Sapling after 10 rounds.
     * Tries to spawn a goomba if it does not grow into a Sapling.
     *
     * @param location The location of the Sprout
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
