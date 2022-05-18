package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public class HealingWater extends Water{
    @Override
    public void applyEffects(Actor actor) {
        actor.increaseMaxHp(50);
    }
    @Override
    public String toString() {
        return "Healing Water";
    }
}
