package game.actors;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.items.Key;

/**
 * The Bowser class is a class that represents the enemy character Bowser in Super Mario.
 * The Bowser class is subclass of the Enemy class and implements the Resettable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 19-May-2022
 * @see Enemy
 * @see Resettable
 */
public class Bowser extends Enemy implements Resettable {
    //Private attributes
    private int x;
    private int y;

    /**
     * Constructor.
     * Calls the constructor from its parent class to implement the basic characteristics of an Enemy character.
     * Adds the statuses FIRE_ATTACK and ENDGAME to its capability set.
     * Saves its coordinates as an attribute for reset purposes.
     *
     * @param x the x ordinate of where Bowser is placed on the map
     * @param y the y ordinate of where Bowser is placed on the map
     */
    public Bowser(int x, int y) {
        super("Bowser", 'B', 500);
        this.addItemToInventory(new Key());
        this.addCapability(Status.FIRE_ATTACK);
        this.addCapability(Status.ENDGAME);
        this.x = x;
        this.y = y;
        this.addToMonologues();
        this.registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon for Bowser.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches");
    }

    /**
     * Interface method - Resets Bowser's HP and move him back to his original position.
     *
     * @param map The GameMap which this object exists in.
     */
    @Override
    public void resetInstance(GameMap map) {
        this.resetMaxHp(this.getMaxHp());
        this.getBehaviours().remove(2);
        map.moveActor(this, map.at(this.x, this.y));
    }

    /**
     * Interface method - adds Bowser's monologue lines to the private ArrayList attribute {@code monologue}.
     */
    @Override
    public void addToMonologues() {
        this.getMonologues().add("What was that sound? Oh, just a fire.");
        this.getMonologues().add("Princess Peach! You are formally invited... to the creation of my new kingdom!");
        this.getMonologues().add("Never gonna let you down!");
        this.getMonologues().add("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");

    }
}
