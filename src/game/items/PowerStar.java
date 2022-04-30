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

    ConsumeAction action = new ConsumeAction(this, Status.POWER_STAR);

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


    /**
     * Getter
     *
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     * @return an unmodifiable list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        if(super.getAllowableActions().contains(this.action)) {
            this.removeAction(this.action);
        }
        if (this.hasCapability(Status.CARRIED)){
            this.addAction(this.action);
        }
        return super.getAllowableActions();
    }
    @Override
    public int getValue() {
        return 0;
    }
}