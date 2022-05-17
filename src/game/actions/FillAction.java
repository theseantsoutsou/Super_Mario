package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class FillAction extends Action {
    private String contents;
    public FillAction(String contents){
        this.contents = contents;
    }

    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + "fills bottle.";
    }
}
