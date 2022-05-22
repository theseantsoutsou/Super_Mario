package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Teleport is an interface that helps manages Ground objects (namely WarpPipes) that allow player to teleport to a
 * different map.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 17-May-2022
 */
public interface Teleport {
    /**
     * Default method that registers a WarpPipe object to TeleportManager
     */
    default void addToTeleportManager(){
        TeleportManager.addWarpPipe(this);
    }

    /**
     * Setter method for the destination of the warp.
     *
     * @param location the destination
     */
    void setDestination(Location location);
}
