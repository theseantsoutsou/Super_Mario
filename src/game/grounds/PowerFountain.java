package game.grounds;

import game.items.PowerWater;
import game.items.Water;

/**
 * The PowerFountain class is a class that represents the Magical Fountain of Power.
 * The PowerFountain class is a subclass of the Fountain class
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 17-May-2022
 * @see Fountain
 */
public class PowerFountain extends Fountain{
    /**
     * Constructor for the PowerFountain class
     * Calls its parent class's constructor to set display character
     */
    public PowerFountain(){
        super('A');
    }

    /**
     * Interface method - getter for a new PowerWater object
     *
     * @return a new PowerWater object
     * @see PowerWater
     */
    public Water getWater(){
        return new PowerWater();
    }

    /**
     * Formats the name of the object as a string
     *
     * @return a String describing the object
     */
    public String toString() {
        return "Power Fountain";
    }
}
