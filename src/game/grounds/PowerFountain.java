package game.grounds;

import game.items.PowerWater;
import game.items.Water;

public class PowerFountain extends Fountain{
    public PowerFountain(){
        super('A');
    }

    public Water getWater(){
        return new PowerWater();
    }

    public String toString() {
        return "Power Fountain";
    }
}
