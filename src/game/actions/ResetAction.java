package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.Resettable;
import game.Status;

/**
 * Class to allow the player to reset predetermined aspects of the game once per life
 */
public class ResetAction extends Action implements Resettable {

    ResetManager resetManager;

    public ResetAction(Actor actor){
        resetManager = ResetManager.getInstance();
    }

    /**
     * When run, resetManager would be called to handle resetting of all status and attributes that
     * have been specified
     * Status RESETTABLE would be removed from player so they can no longer reset the game.
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        resetManager.run();
        actor.removeCapability(Status.RESETTABLE);
        return "Game has been reset";
    }


    /**
     * Attributes and the like should be reset here
     * Classes call for this method in order to reset attributes (enemies removed, player resets status,
     *trees have a 50% chance of becoming dirt, coins on the ground are removed)
     */
    @Override
    public void resetInstance() {

    }

    /**
     * Objects such as Goomba/Koopa, Tree, Player, Coins would be added to resettableList through this
     * method
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
        resetManager.appendResetInstance(this);
    }

    /**
     * Which key the player would need to press in order to reset the game
     * @return
     */
    @Override
    public String hotkey() {return "r";}

    /**
     * What the game would say when 'r' is pressed
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor + " resets game";
    }
}
