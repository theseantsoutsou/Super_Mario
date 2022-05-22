package game.items;

import edu.monash.fit2099.engine.actors.Actor;
/**
 * Abstract base Water class
 * @author Connor McCloud-Gibson
 * @since 17/05/22
 */
public abstract class Water implements Consumable {
    @Override
    public abstract void applyEffects(Actor actor);
}
