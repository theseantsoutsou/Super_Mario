package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Fire;

public class FirePunchAction extends BasicAttackAction{

    protected Fire fireball;

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public FirePunchAction(Actor target, String direction) {
        super(target, direction);
        this.fireball = new Fire();
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        String result = super.execute(actor, map);
        map.locationOf(this.target).addItem(this.fireball);

        return result + this.result(map);
    }
}
