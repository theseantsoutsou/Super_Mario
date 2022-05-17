package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

public interface Spawnable {
    /**
     * Spawnable instances have a chance to spawn other objects
     *
     * @param location the location of the instance
     */
    void spawn(Location location);
}
