package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.FireFlower;
import game.npcs.Goomba;

import java.util.Random;


public abstract class Tree extends HighGround implements Spawnable{
    //Private Attributes
    private int age;

    /**
     * Constructor.
     *
     * @param displayChar     character to display for this type of terrain
     * @param jumpSuccessRate
     * @param fallDamage
     */
    public Tree(char displayChar, int jumpSuccessRate, int fallDamage) {
        super(displayChar, jumpSuccessRate, fallDamage);
        this.age = 1;
    }

    public int getAge() {
        return this.age;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Tree's age;
     *
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        this.age++;
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
        if (this.hasCapability(Status.CAN_GROW) ){
                //&& r.nextInt(10) <= 5) {
            location.addItem(new FireFlower(true));
        }
    }

}
