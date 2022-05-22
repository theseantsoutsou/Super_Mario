package game.grounds;

import game.items.HealingWater;
import game.items.Water;

/**
 * The HealthFountain class is a class that represents the Magical Fountain of Health.
 * The HealthFountain class is a subclass of the Fountain class
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 17-May-2022
 * @see Fountain
 */
public class HealthFountain extends Fountain{
    /**
     * Constructor for the HealthFountain class
     * Calls its parent class's constructor to set display character
     */
    public HealthFountain(){
        super('H');
    }

    /**
     * Interface method - getter for a new HealingWater object
     *
     * @return a new HealingWater object
     * @see HealingWater
     */
    @Override
    public Water getWater() {
        return new HealingWater();
    }

    /**
     * Formats the name of the object as a string
     *
     * @return a String describing the object
     */
    @Override
    public String toString() {
        return "Healing Fountain";
    }
}
