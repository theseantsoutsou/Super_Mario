package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Fire;

public class FireAttackAction extends AttackAction {

    protected Fire fireball;

    public FireAttackAction(Actor target, String direction){
        super(target, direction);
        this.fireball = new Fire();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(this.target).addItem(this.fireball);

        return this.result(map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " with fire";
    }
}
