package game.actors;

import java.util.ArrayList;

/**
 * Speaks is an interface that registers objects of the Actor class (and its subclasses) and grant them the ability
 * to speak.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 19-May-2022
 * @see game.grounds.Fountain
 */
public interface Speaks {

    /**
     * Default method that registers the actor to the ActorManager as "able to speak".
     */
    default void registerSpeech(){
        ActorManager.getInstance().addToSpeakingActorsList(this);
    }

    /**
     * Getter for the actor's monologues
     *
     * @return a list of monologues
     */
    ArrayList<String> getMonologues();

    /**
     * Stores an actor's monologues to its attribute {@code monologues}
     */
    void addToMonologues();
}
