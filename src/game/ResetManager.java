package game;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * The ResetManager is a singleton class that does soft-reset on the instances
 *
 * @author FIT2099 Teaching Team, Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;

    private static ResetManager instance; //A singleton reset manager instance

    /**
     * Private constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Get the singleton instance of reset manager
     *
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Reset the game by traversing through a list of objects that implement
     * the interface Resettable.
     */
    public void run(GameMap map){
        for (Resettable reset: resettableList){
            reset.resetInstance(map);
        }
    }

    /**
     * Add the Resettable instance to the list. Use in constructor of Resettable objects.
     *
     * @param reset an object that implements Resettable
    **/
    public void appendResetInstance(Resettable reset){
        resettableList.add(reset);
    }


    /**
     * Remove a Resettable instance from the list
     *
     * @param resettable resettable object
     */
    public void cleanUp(Resettable resettable){
        resettableList.remove(resettable);
    }
}
