package game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
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
public class Bowser extends Actor {
    //Private attributes
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour
    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(2, new FollowBehaviour());
        this.addItemToInventory(new Key());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
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

}
