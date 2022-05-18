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
 * notes:
 * spawning of fire flower to be done in sprout and sapling (maybe can be done here? as code is repeated)
 *
 */
public class FireFlower extends Item implements Consumable{

    private Enum<Status> capability = Status.FIRE_ATTACK ;

    /**
     * Constructor
     *
     */
    public FireFlower(boolean portable) {
        super("Fire Flower", 'f', portable);
        this.addToConsumablesManager();
    }

    public FireFlower(){
        super("Fire Flower", 'f', true);
        this.addToConsumablesManager();
    }

    @Override
    public List<Action> getAllowableActions(){
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeAction(this));

        return actions;
    }


    @Override
    public void applyEffects(Actor actor) {
        actor.addCapability(capability);
    }
}
