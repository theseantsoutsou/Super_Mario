package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The TeleportManager class is a singleton class that manages objects that implement Teleport interface, namely WarpPipes.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 17-May-2022
 * @see WarpPipe
 * @see Teleport
 */
public class TeleportManager {
    // Private attributes
    private static TeleportManager instance;
    private static HashMap<String, GameMap> gameMaps = new HashMap<String, GameMap>();
    private static List<Teleport> warpPipes= new ArrayList<>();

    /**
     * Registers a WarpPipe object to the list of Teleport instances
     *
     * @param warpPipe a warp pipe object that implements Teleport
     */
    public static void addWarpPipe(Teleport warpPipe) {
        warpPipes.add(warpPipe);
    }

    /**
     * Getter for the WarpPipe stored in the attribute list warpPipes
     * @param warpPipe WarpPipe to be checked in the list
     * @return a WarpPipe that implements Teleport
     */
    public static Teleport getWarpPipe(Ground warpPipe){
        return warpPipes.get(warpPipes.indexOf(warpPipe));
    }

    /**
     * Getter for the attribute Map gameMaps.
     *
     * @return a Map of gameMaps
     */
    public static HashMap<String, GameMap> getGameMaps() {
        return gameMaps;
    }

    /**
     * Registers a GameMap to TeleportManager
     *
     * @param locationName a String describing the map
     * @param map the game map
     */
    public static void addGameMap(String locationName, GameMap map) {
        gameMaps.put(locationName, map);
    }

    /**
     * Static instance method for TeleportManager.
     *
     * @return TeleportManger instance
     */
    public static TeleportManager getInstance() {
        if (instance == null) {
            instance = new TeleportManager();
        }
        return instance;
    }
}
