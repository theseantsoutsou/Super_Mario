package game.actors;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.behaviours.SpeechBehaviour;
import game.items.Key;

import java.util.ArrayList;

/**
 * The Bowser class is a class that represents the non-playable character Bowser in Super Mario.
 * The Bowser class is subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Bowser extends Enemy implements Resettable {
    //Private attributes
    private int x;
    private int y;
    /**
     * Constructor.
     */
    public Bowser(int x, int y) {
        super("Bowser", 'B', 500);
        this.addItemToInventory(new Key());
        this.addCapability(Status.FIRE_ATTACK);
        this.addToMonologues();
        this.x = x;
        this.y = y;
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



    @Override
    public void addToMonologues() {
        this.getMonologues().add("What was that sound? Oh, just a fire.");
        this.getMonologues().add("Princess Peach! You are formally invited... to the creation of my new kingdom!");
        this.getMonologues().add("Never gonna let you down!");
        this.getMonologues().add("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");

    }
}
