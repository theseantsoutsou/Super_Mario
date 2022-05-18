package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Water;

public class FillAction extends Action {
    private Bottle bottle;
    private Water water;
    public FillAction(Water water, Bottle bottle){
        this.water= water;
        this.bottle = bottle;
    }

    public String execute(Actor actor, GameMap map) {
        this.bottle.fill(water);
        return actor.toString() + " fills bottle with " + water.toString();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " fills bottle.";
    }
}
