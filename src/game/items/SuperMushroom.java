package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.Status;
import game.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

/***
 * Things SuperMushroom are able to do/ what it does/ all the qualities SuperMushroom has
 * can be picked up
 * can be traded
 * can be dropped (dropped when koopa shell is atomized)
 * increases player's max HP by 50 (does not heal)
 * m -> M
 * 100% jump rate and no fall damage
 * M -> m, no 100% jump when player receives damage (if current hp < hp at start of tick)
 */
public class SuperMushroom extends Item implements TradableItem {
    ConsumeAction action = new ConsumeAction(this, Status.TALL);
    /**
     * Constructor
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
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