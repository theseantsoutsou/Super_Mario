package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.DrinksWater;
import game.actors.ActorManager;
/**
 * Extension of abstract Water class.
 * @author Connor McCloud-Gibson
 * @since 17/05/22
 */
public class PowerWater extends Water {
    @Override
    /**
     * Apply effects of "Power Water" to the actor which consumes it.
     */
    public void applyEffects(Actor actor) {
        DrinksWater effectedActor = ActorManager.getInstance().getActorThatDrinks(actor);
        if(effectedActor!=null){effectedActor.updateBaseAttack(15);}
    }
    @Override
    public String toString() {
        return "Power Water";
    }
}
