package game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.items.Key;

import java.util.Map;
import java.util.TreeMap;

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
}
