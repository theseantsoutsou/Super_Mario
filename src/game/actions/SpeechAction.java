package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Speaks;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * The SpeakAction class is a special Action for Toad to say random lines based on the player's capabilities
 * (i.e. should not say first line if the player has a wrench in their inventory)
 * The SpeakAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
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
     * Checks the capabilities of the player to determine which lines would make sense for the NPC target to say.
     *
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
        return String.format("Speak with %s.", target.toString());
    }

}
