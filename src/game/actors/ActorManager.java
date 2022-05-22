package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * The actor manager class is a singleton class that is intended to store a reference to
 * actors that implement specific interfaces, which introduce actions to that actor class.
 * This class currently keeps an array of actors which implement "Speaks" and "DrinksWater".
 *
 * @author Connor McCloud-Gibson
 * @since 18/05/22
 */
public class ActorManager {
    /**
     * Singleton instance
     */
    private static ActorManager instance;

    /**
     * Uses Actor to search in array and returns a reference that Actor which implements the interface "DrinksWater".
     * Returns null if Actor does not implement "DrinksWater"
     * @param actor
     * @return DrinksWater
     */
    public DrinksWater getActorThatDrinks(Actor actor) {
        int index = this.hasBaseAttack.indexOf(actor);
        if(index>=0) {return this.hasBaseAttack.get(index);}
        return null;
    }
    /**
     * Uses Actor to search in array and returns a reference to that Actor which implements the interface "Speaks".
     * Returns null if Actor does not implement "Speaks"
     * @param actor
     * @return Speaks
     */
    public Speaks getActorThatSpeaks(Actor actor) {
        int index = this.actorThatSpeaks.indexOf(actor);
        if(index>=0) {return this.actorThatSpeaks.get(index);}
        return null;
    }

    /**
     * ArrayList which is used to store references to Actor objects that implement the interface "DrinksWater"
     */
    private ArrayList<DrinksWater> hasBaseAttack = new ArrayList<DrinksWater>();

    /**
     * ArrayList which is used to store references to Actor objects that implement the interface "Speaks"
     */
    private ArrayList<Speaks> actorThatSpeaks = new ArrayList<Speaks>();

    /**
     * Store reference to Actor object that implements DrinkWater
     * @param actor
     */
    public void addToBaseAttackList(DrinksWater actor){
        hasBaseAttack.add(actor);
    }
    /**
     * Store reference to Actor object that implements Speaks
     * @param actor
     */

    public void addToSpeakingActorsList(Speaks actor){
        actorThatSpeaks.add(actor);
    }

    /**
     * Getter that instantiates an instance of ActorManager class (if it does not already exist) and returns a reference to it.
     * @return ActorManager
     */
    public static ActorManager getInstance(){
        if(instance==null){
            instance=new ActorManager();
        }
        return instance;
    }
}
