package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Toad;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * The ToadSpeechAction class is a special Action for Toad to say random lines based on the player's capabilities
 * (i.e. should not say first line if the player has a wrench in their inventory)
 * The SpeakAction class is a subclass of the Action class.
 *
 * @author Connor Gibson
 * @version 2.0
 * @since 17-May-2022
 */
public class ToadSpeechAction extends SpeechAction {
    //Private attributes
    private Toad target;
    private Random rand = new Random();
    /**
     * Constructor.
     *
     * @param target The actor that player can speak to.
     */
    public ToadSpeechAction(Toad target){
        super(target);
        this.target = target;
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

        int randVal = 0;
        int bound = target.getMonologues().size();
        if(actor.hasCapability(Status.POWER_STAR) && actor.hasCapability(Status.BREAK_SHELL)) {
                randVal = 2+rand.nextInt(bound-2);
        }
        else if(actor.hasCapability(Status.BREAK_SHELL)){
            randVal = 1+rand.nextInt(bound-1);
        }
        else if (actor.hasCapability(Status.POWER_STAR)) {
            randVal = (2+rand.nextInt(bound-1))%bound;
        }
        else{randVal = rand.nextInt(bound);}
        return this.target.toString() + ": " + this.target.getMonologues().get(randVal);

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
