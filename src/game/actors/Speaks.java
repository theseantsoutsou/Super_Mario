package game.actors;

import game.actions.SpeechAction;

import java.util.ArrayList;

public interface Speaks {

    ArrayList<String> getMonologues();
    default void registerSpeech(){
        ActorManager.getInstance().addToSpeakingActorsList(this);
    }
    void addToMonologues();
}
