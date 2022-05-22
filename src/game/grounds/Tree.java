package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.FireFlower;

import java.util.Random;

/**
 * The Tree class is an abstract class that represent Trees and all its stages in its life cycle.
 * The Tree class is a subclass of the HighGround class and implements spawnable.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 18-May-2022
 */
public abstract class Tree extends HighGround implements Spawnable{
    //Private Attributes
    private int age;

    /**
     * Constructor for the Tree class.
     * Calls its parent class's constructor to set display character, jump success rate, and fall damage.
     * Initializes age to 1.
     *
     * @param displayChar       character to display for this type of terrain
     * @param jumpSuccessRate   the success rate of jumping onto this terrain
     * @param fallDamage        the damage taken from falling from this terrain
     */
    public Tree(char displayChar, int jumpSuccessRate, int fallDamage) {
        super(displayChar, jumpSuccessRate, fallDamage);
        this.age = 1;
    }

    /**
     * Getter method for the age attribute.
     *
     * @return the age of this object
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Tree's age;
     *
     * @param location the location of the Tree
     */
    @Override
    public void tick(Location location) {
        this.age += 1;
    }

    /**
     * Interface method - Tree has a 50 percent chance of spawning a FireFlower at its location during its growing phases.
     *
     * @param location the location of the Sprout
     * @see Status#CAN_GROW
     */
    @Override
    public void spawn(Location location) {
        Random r = new Random();
        if (this.hasCapability(Status.CAN_GROW) && r.nextInt(10) <= 5) {
            location.addItem(new FireFlower());
        }
    }

}
