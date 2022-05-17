package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;

import java.util.ArrayList;
import java.util.Stack;

public class Bottle extends Item implements ConsumableItem, TradableItem{
    private ArrayList<String> contents = new ArrayList<>();
    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        this.addCapability(Status.FILLABLE);
    }

    @Override
    public void applyEffects(Actor actor) {

    }

    @Override
    public Enum<Status> getCapability() {
        return null;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public String toString() {
        String name = "[";
        for(int i =0; i< contents.size();i++){
            if(i != contents.size()-1){
             name += contents.get(i) + ", ";
            }
            else{
                name+= contents.get(i) + "]";
            }
        }
        return super.toString()+name;
    }
}
