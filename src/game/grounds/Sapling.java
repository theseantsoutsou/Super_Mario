package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Coin;
import game.items.FireFlower;

import java.util.Random;

/**
 * The Sapling class is a class that represents the second stage of a tree's life cycle.
 * The Sapling class is a subclass of the Ground class and implements the Jumpable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Sapling extends Tree {

    /**
     * Constructor for the Sapling class.
     * Calls its parent class Ground class's constructor to set display character.
     * Initializes its age to 0 and adds a HIGH_GROUND status to its capability.
     *
     * @see Status#HIGH_GROUND
     */
    public Sapling() {
        super('t', 80, 20);
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Sapling's age; grows into a tree after 10 rounds.
     * Tries to spawn a $20 coin if it does not grow into a Tree.
     *
     * @param location The location of the Sapling
     */
    @Override
    public void tick(Location location) {
        if (this.getAge() == 10) {
            location.setGround(new Mature());
            this.addCapability(Status.CAN_GROW);
        }
        this.spawn(location);
        super.tick(location);
    }

    /**
     * Interface method - Saplings have a 10 percent chance of spawning a coin at its location.
     *
     * @param location the sapling's location
     * @see Coin
     */
    @Override
    public void spawn(Location location) {
        Random r = new Random();

        if (this.getAge() < 10 && r.nextInt(100) <= 10) {
            location.addItem(new Coin(location, 20));
        }
        super.spawn(location);
    }
}
