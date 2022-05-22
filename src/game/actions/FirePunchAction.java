package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Fire;

/**
 * FirePunchAction is a special attack which attacks another Actor with both a punch and by using fire
 * @author Sean Tsou
 * @since 18/05/22
 */
public class FirePunchAction extends BasicAttackAction{
    /**
     * Fire item which is to be spawned on the map by the action.
     */
    protected Fire fireball;

    /**
     * Constructor
     *
     * @param target the Actor to attack
     * @param direction direction of the attack
     */
    public FirePunchAction(Actor target, String direction) {
        super(target, direction);
        this.fireball = new Fire();
    }

    /**
     * Execute the fre punch action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = super.execute(actor, map);
        map.locationOf(this.target).addItem(this.fireball);

        return result + this.result(map);
    }
}
