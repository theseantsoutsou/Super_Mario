package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public abstract class Water implements Consumable {
    @Override
    public abstract void applyEffects(Actor actor);
}
