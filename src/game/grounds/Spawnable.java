package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Spawnable is an interface that describes Ground objects that can spawn other objects.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 17-May-2022
 */
public interface Spawnable {
    /**
     * Spawnable instances have a chance to spawn other objects
     *
     * @param location the location of the instance
     */
    void spawn(Location location);
}
