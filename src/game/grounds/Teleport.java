package game.grounds;

import edu.monash.fit2099.engine.positions.Location;

public interface Teleport {
    default void addToTeleportManager(){
        TeleportManager.addWarpPipe(this);
    }
    void setDestination(Location location);
}
