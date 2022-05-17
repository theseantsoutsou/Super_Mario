package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeleportManager {
    static private TeleportManager instance;
    static protected HashMap<String, GameMap> gameMaps = new HashMap<String, GameMap>();

    public static void addWarpPipe(Teleport warpPipe) {
        TeleportManager.warpPipes.add(warpPipe);
    }
    public static Teleport getWarpPipe(Ground warpPipe){
        return warpPipes.get(warpPipes.indexOf(warpPipe));
    }

    static private List<Teleport> warpPipes= new ArrayList<>();

    public static HashMap<String, GameMap> getGameMaps() {
        return gameMaps;
    }

    public static void addGameMap(String locationName, GameMap map) {
        TeleportManager.gameMaps.put(locationName, map);
    }

    public static TeleportManager getInstance() {
        if (instance == null) {
            instance = new TeleportManager();
        }
        return instance;
    }
}
