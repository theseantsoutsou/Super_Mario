package game;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface that allows objects to be added to a list of resettable objects and
 * allows Reset manager to reset a specific object to its default state
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     *
     * @param map The GameMap which the current instance exists in.
     */
    void resetInstance(GameMap map);

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
