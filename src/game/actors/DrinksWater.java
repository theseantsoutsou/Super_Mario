package game.actors;

/**
 * DrinksWater is an interface that registers objects of the Actor class (and its subclasses) and grant them the ability
 * to drink water from the Magical Fountain.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 19-May-2022
 * @see game.grounds.Fountain
 */
public interface DrinksWater {
    /**
     * Default method that registers the actor to the ActorManager as "able to drink from fountain".
     */
    default void registerDrinks(){
        ActorManager.getInstance().addToBaseAttackList(this);
    }

    /**
     * Updates actor's base attack value after drinking from the Power Fountain
     *
     * @param value the value which the base attack increases by
     * @see game.grounds.PowerFountain
     */
    void updateBaseAttack(int value);

    /**
     * Getter for the actor's base attack.
     *
     * @return an int representing the actor's base attack value
     */
    int getBaseAttack();
}
