package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeechAction;
import game.actors.Speaks;

public class SpeechBehaviour implements Behaviour {
    Speaks speaker;
    int turns = -1;
    public SpeechBehaviour(Speaks speaker){
        this.speaker = speaker;

    }
    public Action getAction(Actor actor, GameMap map) {
        turns+=1;
        if(turns%2==0){
            return new SpeechAction(speaker);
        }
        return null;
    }
}
