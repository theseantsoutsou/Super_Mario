package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

import java.util.Random;

/**
 * Special Action that allows Toad to say random lines based on the player's capabilities
 * (i.e. should not say first line if the player has a wrench in their inventory)
 */
public class SpeakAction extends Action {
    private Actor target;
    protected Random rand = new Random();
    public SpeakAction(Actor target){
        this.target=target;
    }

    /**
     * Checks the capabilities of the player to determine to determine which lines would make sense
     * for toad to say
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
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
     * Display description to speak to Toad on the menu
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Speak with %s.", target.toString());
    }
}
