package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Coin;
import game.items.FireFlower;

import java.util.Random;

/**
 * The Sapling class is a class that represents the second stage of a tree's life cycle.
 * The Sapling class is a subclass of the Tree class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 02-May-2022
 */
public class Sapling extends Tree {

    /**
     * Constructor for the Sapling class.
     * Calls its parent class's constructor to set display character, jump success rate, and fall damage
     */
    public Sapling() {
        super('t', 80, 20);
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, check Sapling's age; grows into a mature tree after 10 rounds and gains the status CAN_GROW
     * Tries to spawn a $20 coin (and FireFlower if its age == 10).
     * Increments age by calling the parent class's tick function.
     *
     * @param location The location of the Sapling
     * @see Status#CAN_GROW
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
     * Calls the parent class's spawn function to try and spawn a FireFlower if conditions are met.
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
