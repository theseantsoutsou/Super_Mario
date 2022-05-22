package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeechAction;
import game.actors.Speaks;

/**
 * SpeechBehaviour allows actors to say their monologue by generating a SpeechAction every 3second turn.
 * @author Connor McCloud-Gibson
 * @since 15/05/22
 */
public class SpeechBehaviour implements Behaviour {
    /**
     * Actor that is speaking
     */
    Speaks speaker;
    int turns = -1;
    /**
     * Constructor
     */
    public SpeechBehaviour(Speaks speaker){
        this.speaker = speaker;

    }

    /**
     * Get the SpeakAction every even amount of turns
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return SpeechAction
     */
    public Action getAction(Actor actor, GameMap map) {
        turns+=1;
        if(turns%2==0){
            return new SpeechAction(speaker);
        }
        return null;
    }
}
