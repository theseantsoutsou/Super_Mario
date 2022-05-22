package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.behaviours.Behaviour;

/**
 * The PiranhaPlant class is a class that represents the enemy character Piranha Plant in Super Mario.
 * The PiranhaPlant class is subclass of the Enemy class and implements the Resettable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 02-May-2022
 * @see Enemy
 * @see Resettable
 */
public class PiranhaPlant extends Enemy implements Resettable {
    /**
     * Constructor for the PiranhaPlant class.
     * Calls its parent class's constructor to set all the characteristics of an Enemy character.
     *
     * @see Status#CAN_SLEEP
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon for PiranhaPlant.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    /**
     * Interface method - Increase and reset Piranha Plant HP upon reset.
     *
     * @param map The GameMap which this object exists in.
     */
    @Override
    public void resetInstance(GameMap map) {
        this.resetMaxHp(this.getMaxHp() + 50);
    }

    /**
     * Interface method - stores PiranhaPlant's monologues
     */
    @Override
    public void addToMonologues() {
        this.getMonologues().add("Slsstssthshs~! (Never gonna say goodbye~)");
        this.getMonologues().add("Ohmnom nom nom nom.");
    }
}
