package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.ConsumeWaterAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class Bottle extends Item {
    private Stack<Water> contents = new Stack<>();

    static int baseAttack = 0;
    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        baseAttack = 0;
    }
    public void fill(Water water){
        contents.push(water);
    }
    public String drink(Actor actor) {
       Water water = contents.pop();
       water.applyEffects(actor);
       return water.toString();
    }

    public static void increaseBaseAttack(int value){
        baseAttack+=value;
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions= new ArrayList<>();
        if(!contents.isEmpty()){actions.add(new ConsumeWaterAction(this));}
        return actions;
    }

    @Override
    public String toString() {
        return super.toString()+contents.toString();
    }

    public static int getBaseAttack(){
        return baseAttack;
    }
}
