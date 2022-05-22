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

/**
 * Item which is used to store water. Uses a stack data structure to store Water objects.
 * @author Connor McCloud-Gibson
 * @since 18/05/22
 */
public final class Bottle extends Item {
    private Stack<Water> contents = new Stack<>();
    /**
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
    }

    /**
     * Store water in the bottle (push onto the stack)
     * @param water
     */
    public void fill(Water water){
        contents.push(water);
    }
    /**
     * Consume water from the bottle (pop off of the stack), apply the effects of the water to the Actor.
     * Return name of type of water consumed.
     * @param actor
     * @return String
     */
    public String drink(Actor actor) {
       Water water = contents.pop();
       water.applyEffects(actor);
       return water.toString();
    }

    /**
     * Get allowable actions for the item (ConsumeWaterAction)
     * @return list of actions
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions= new ArrayList<>();
        if(!contents.isEmpty()){actions.add(new ConsumeWaterAction(this));}
        return actions;
    }

    /**
     * Get name of Class as string
     * @return String
     */
    @Override
    public String toString() {
        return super.toString()+contents.toString();
    }
}
