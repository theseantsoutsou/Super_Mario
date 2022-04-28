package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action that does nothing
 */
public class EmptyAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {

        return System.lineSeparator();
    }

    @Override
    public String menuDescription(Actor actor) {
        return System.lineSeparator();
    }
}
