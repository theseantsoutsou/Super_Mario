package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

import java.util.Random;

/**
 * The SpeakAction class is a special Action for Toad to say random lines based on the player's capabilities
 * (i.e. should not say first line if the player has a wrench in their inventory)
 * The SpeakAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class SpeakAction extends Action {
    //Private attributes
    private Actor target;
    private Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target The actor that player can speak to.
     */
    public SpeakAction(Actor target){
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
        int randVal = 0;
        if(actor.hasCapability(Status.POWER_STAR) && actor.hasCapability(Status.BREAK_SHELL)) {
            while (randVal > 1) {
                randVal = rand.nextInt(4);
            }
        }
        else if(actor.hasCapability(Status.BREAK_SHELL)){
            while(randVal!=0){
                randVal = rand.nextInt(4);
            }
        } else if (actor.hasCapability(Status.POWER_STAR)) {
            while (randVal != 1) {
                randVal = rand.nextInt(4);
            }
        }
            else{randVal = rand.nextInt(4);}

        switch(randVal){
            case 0:
                return "You might need a wrench to smash Koopa's hard shells.";
            case 1:
                return "You better get back to finding the Power Stars.";
            case 2:
                return "The Princess is depending on you! You are our only hope.";
            case 3:
                return "Being imprisoned in these walls can drive a fungus crazy :(";
        }
        return "";
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
