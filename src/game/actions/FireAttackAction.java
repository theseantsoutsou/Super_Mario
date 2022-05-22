package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Fire;

/**
 * FireAttackAction is a class which extends AttackAction and allows Actor to attack other Actors by spawning Fire
 * items on the map.
 * @author Lucus Choy, Sean Tsou
 * @since 18/05/22
 */
public class FireAttackAction extends AttackAction {
    /**
     * Fire item which is spawned on the map by the action.
     */
    protected Fire fireball;

    /**
     * Constructor
     * @param target intended target Actor
     * @param direction of the attack
     */
    public FireAttackAction(Actor target, String direction){
        super(target, direction);
        this.fireball = new Fire();
    }

    /**
     * Spawn fire on the map by adding Fireball to the location of the target
     * @param actor
     * @param map
     */
    @Override
    public void implementAttack(Actor actor, GameMap map) {
        map.locationOf(this.getTarget()).addItem(this.fireball);
    }

    @Override
    /**
     * Descriptor of the attack action.
     * @returns String
     */
    public String menuDescription(Actor actor) {
        return actor + " attacks " + this.getTarget() + " with fire";
    }
}
