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
    public void implementAttack(Actor actor, GameMap map) {
        map.locationOf(this.getTarget()).addItem(this.fireball);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + this.getTarget() + " with fire";
    }
}
