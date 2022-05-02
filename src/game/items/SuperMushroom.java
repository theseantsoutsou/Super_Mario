package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.ConsumeAction;

import java.util.List;

/**
 * The SuperMushroom class is a class that represents the item that makes Mario grow.
 * The SuperMushroom class is a subclass of the Item class and implements the TradableItem interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class SuperMushroom extends Item implements TradableItem {
    //Private attribute
    private int value = 400;

    /**
     * Constructor for SuperMushroom - takes in boolean portable, which sets the portability of the item.
     * (any item to be traded should not be portable)
     * @param portable Boolean value, true if portable, false otherwise.
     */
    public SuperMushroom(boolean portable) {
        super("Super Mushroom", '^', portable);
        this.addToInventory();
    }
    /**
     * Overloaded constructor for SuperMushroom allows for instantiation without portability being specified.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.addToInventory();
    }

    /**
     * Check if the SuperMushroom has been paired with a ConsumeAction in the ConsumableItemManager and if the
     * SuperMushroom is being carried. If it has been paired, remove the ConsumeAction to ensure player cannot consume
     * the SuperMushroom if it is on the ground.
     * (i.e. picking up the item then dropping it should remove the ConsumeAction).
     * Otherwise, if the SuperMushroom is simply being carried, add a new ConsumeAction.
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        if (ConsumableItemManager.getInstance().getConsumeAction(this)!= null
                && !this.hasCapability(Status.CARRIED)) {
            this.removeAction(ConsumableItemManager.getInstance().getConsumeAction(this));
        }
        else if (this.hasCapability(Status.CARRIED)){
            this.addAction(new ConsumeAction(this, Status.TALL));
        }
        return super.getAllowableActions();
    }

    /**
     * Interface method - getter for SuperMushroom's value.
     */
    @Override
    public int getValue() {
        return this.value;
    }
}
