package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action that lets an actor commit suicide (Goomba)
 */
public class SuicideAction extends Action {

    /**
     * Removes the actor from the game map
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        map.removeActor(actor);

        return menuDescription(actor);
    }

    /**
     * Outputs descriptor of action.
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+ " killed itself";
    }
}
