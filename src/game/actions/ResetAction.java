package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.Resettable;
import game.Status;

/**
 * The ResetAction class is a special Action for the player to reset predetermined aspects of the game once per life.
 * The ResetAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class ResetAction extends Action {
    //Private attribute
    private ResetManager resetManager;

    /**
     * Constructor for ResetAction class.
     * Stores the singleton ResetManager's instance
     */
    public ResetAction(){
        resetManager = ResetManager.getInstance();
    }

    /**
     * When run, resetManager would be called to handle resetting of all status/attributes and objects specified.
     * Status RESETTABLE would be removed from the actor so that they can no longer reset the game.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A String indicating the game has been reset
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        resetManager.run(map);
        actor.removeCapability(Status.RESETTABLE);
        return "Game has been reset";
    }

    /**
     * Which key the player would need to press in order to reset the game
     *
     * @return "r"
     */
    @Override
    public String hotkey() {return "r";}

    /**
     * Display description that the actor can reset the game
     *
     * @param actor The actor performing the action.
     * @return A String to add to actor's menu of options.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets game";
    }
}
