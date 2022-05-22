package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Extension of abstract Water class.
 * @author Connor McCloud-Gibson
 * @since 17/05/22
 */
public class HealingWater extends Water{
    @Override
    /**
     * Apply effects of "healing Water" to the actor which consumes it.
     */
    public void applyEffects(Actor actor) {
        actor.increaseMaxHp(50);
    }
    @Override
    public String toString() {
        return "Healing Water";
    }
}
