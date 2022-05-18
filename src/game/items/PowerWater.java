package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public class PowerWater extends Water {
    @Override
    public void applyEffects(Actor actor) {
        Bottle.increaseBaseAttack(15);
    }
    @Override
    public String toString() {
        return "Power Water";
    }
}
