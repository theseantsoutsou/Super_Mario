package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.DrinksWater;
import game.actors.ActorManager;

public class PowerWater extends Water {
    @Override
    public void applyEffects(Actor actor) {
        DrinksWater effectedActor = ActorManager.getInstance().getActorThatDrinks(actor);
        if(effectedActor!=null){effectedActor.updateBaseAttack(15);}
    }
    @Override
    public String toString() {
        return "Power Water";
    }
}
