package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;
import game.npcs.FlyingKoopa;
import game.npcs.Koopa;

import java.util.List;
import java.util.Random;

/**
 * The Tree class is a class that represents the final stage of a tree's life cycle.
 * The Tree class is a subclass of the Ground class and implements the Jumpable and Resettable interfaces.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Tree extends HighGround implements Spawnable, Resettable {

    //Private Attributes
    private int age;

    private Location location;

    /**
     * Constructor for the Tree class.
     * Calls its parent class Ground class's constructor to set display character.
     * Initializes its age to 0 and adds a HIGH_GROUND status to its capability.
     *
     * @see Status#HIGH_GROUND
     */
    public Tree() {
        super('T', 70, 30);
        this.age = 1;
        this.registerInstance();
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Tree's age; spawns a new sprout every 5 rounds randomly.
     * Has a 20 percent chance to wither and die every turn
     * If it does not die, it has a 15 percent chance of spawning a Koopa at its location.
     *
     * @param location The location of the Tree
     */
    @Override
    public void tick(Location location) {
        this.location = location;
        if (this.age %5 == 0) {
            this.growSprout(location);
        }
        if (!this.die(location)) {
            this.spawn(location);
        }
        this.age++;
    }

    /**
     * Interface method - Trees have a 15 percent chance of spawning a Koopa if an actor is not on it.
     *
     * @param location the location of the Tree
     * @see Koopa
     */
    @Override
    public void spawn(Location location) {
        Random r = new Random();
        if (r.nextInt(100) <= 15 && !location.containsAnActor()) {
            if (r.nextInt(10) <= 5) {
                location.addActor(new Koopa());
            }
            else {
                location.addActor(new FlyingKoopa());
            }
        }
    }

    /**
     * Randomly spawn a sprout at a fertile ground around the Tree.
     *
     * @param location the location of the Tree.
     * @see Status#FERTILE
     */
    public void growSprout(Location location) {
        List<Exit> exits = location.getExits();
        Random r = new Random();
        boolean grown = false;

        while (!grown) {
            int idx = r.nextInt(exits.size());
            if (exits.get(idx).getDestination().getGround().hasCapability(Status.FERTILE)){
                exits.get(idx).getDestination().setGround(new Sprout());
                grown = true;
            }
        }
    }

    /**
     * Trees have a 20 percent chance to wither and die every round.
     *
     * @param location the location of the Tree
     * @return true if the Tree dies, false otherwise
     */
    public boolean die(Location location) {
        boolean dead = false;
        Random r = new Random();
        if (r.nextInt(100) <= 20) {
            location.setGround(new Dirt());
            dead = true;
        }
        return dead;
    }

    /**
     * Interface method - 50% chance for a Tree to turn to dirt upon game resetting.
     *
     * @param map The GameMap which this object exists in.
     */
    @Override
    public void resetInstance(GameMap map){
        Random random = new Random();
        if(random.nextInt(10) < 5) {
            this.location.setGround(new Dirt());
        }
    }
}
