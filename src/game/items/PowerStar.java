package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.Status;
import game.actions.ConsumeAction;

import java.util.List;

/***
 * PowerStar Attributes:
 * can be picked up
 * can be traded
 * is in the game for 10 turns (ground and player inventory, resets upon pickup)
 * heals 200 hit points
 * takes no damage
 * invincibility lasts for 10 turns upon activation
 */

public class PowerStar extends Item implements TradableItem{

    private Actor consumer;

    public PowerStar() {
        super("Power Star", '*', true);
        this.addToInventory();
    }
    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     *
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        this.consumer = actor;
        return new PickUpItemAction(this);
    }

    /**
     * Getter
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        if (this.consumer != null && this.consumer.getInventory().contains(this)) {
            this.addAction(new ConsumeAction(this, Status.POWER_STAR));
        }
        return super.getAllowableActions();
    }

    public int getValue() {
        return 0;
    }
}
