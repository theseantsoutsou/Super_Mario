package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import game.items.HealingWater;
import game.items.Water;

public class HealthFountain extends Fountain{
    public HealthFountain(){
        super('H');
    }

    @Override
    public Water getWater() {
        return new HealingWater();
    }

    @Override
    public String toString() {
        return "Healing Fountain";
    }
}
