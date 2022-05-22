package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Speaks;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * The SpeakAction class is an Action which allows actors that implement the Speaks interface
 * to have random monologues.
 *
 * @author Connor Gibson
 * @version 2.0
 * @since 18-May-2022
 */
public class SpeechAction extends Action {
    //Private attributes
    private Speaks target;
    private Random rand = new Random();



    /**
     * Constructor.
     *
     * @param target The actor that player can speak to.
     */
    public SpeechAction(Speaks target){
        this.target=target;
    }

    /**
     * Execute SpeakAction.
     * Retrieves list of monologues and randomly selects one.
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A String that provide hints to the player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int bound = target.getMonologues().size();
        if(bound == 0){return "";}
        return this.target.toString() + ": " + this.target.getMonologues().get(rand.nextInt(bound));
    }

    /**
     * Display description to speak to the target actor on the menu
     *
     * @param actor The actor performing the action.
     * @return A String to add to actor's menu of options
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
