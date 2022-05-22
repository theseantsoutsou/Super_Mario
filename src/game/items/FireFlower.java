package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Class of FireFlower Item. Consumeable Item which grants the player the ability to use FireAttackAction
 * through the FIRE_ATTACK capability.
 * @author Lucus Choy, Sean Tsou
 * @since 20/05/22
 */
public class FireFlower extends Item implements Consumable{
    /**
     * Status enum used for capabilities.
     */
    private Enum<Status> capability = Status.FIRE_ATTACK ;

    /**
     * Constructor
     */
    public FireFlower() {
        super("Fire Flower", 'f', false);
        this.addToConsumablesManager();
    }

    @Override
    public List<Action> getAllowableActions(){
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeAction(this));

        return actions;
    }

    /**
     * Implementation of method from Consumable interface. Applies the effects of FireFlower to the Actor.
     * @param actor
     */
    @Override
    public void applyEffects(Actor actor) {
        actor.addCapability(capability);
    }
}
