package game.grounds;

/**
 * Jumpable is an interface that contains methods for Ground class's subclasses that are considered high-grounds.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */

public interface Jumpable {

    /**
     * Abstract getter method for the jumping success rate of a high-ground
     *
     * @return the success rate of jumping onto the high-ground
     */
    int getSuccessRate();

    /**
     * Abstract getter method for the fall damage from a high-ground if jump fails
     *
     * @return the fall damage from the high-ground
     */
    int getFallDamage();

}
