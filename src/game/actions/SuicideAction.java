package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * The SuicideAction class is a special Action for an actor to commit suicide
 * The SuicideAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class SuicideAction extends Action {

    /**
     * Removes the actor from the game map
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A String describing the outcome of this SuicideAction.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        map.removeActor(actor);

        return menuDescription(actor);
    }

    /**
     * Outputs descriptor of action.
     *
     * @param actor The actor performing the action.
     * @return A String describing who killed itself.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " killed itself";
    }
}
