package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;

import java.util.List;

/**
 * The PowerStar class is a class that represents the item that makes Mario invincible.
 * The PowerStar class is a subclass of the Item class and implements the TradableItem interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class PowerStar extends Item implements TradableItem{
    //Private attributes
    private int value = 0;
    private int age;

    /**
     * Constructor for PowerStar - takes in boolean portable, which sets the portability of the item.
     * (any item to be traded should not be portable)
     * @param portable Boolean value, true if portable, false otherwise.
     */
    public PowerStar(boolean portable) {
        super("Power Star", '*', portable);
        this.age = 0;
        this.addToInventory();
    }

    /**
     * Overloaded constructor for PowerStar allows for instantiation without portability being specified.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        this.age = 0;
        this.addToInventory();
    }

    /**
     * Inform a carried Item of the passage of time.
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        this.age += 1;
        if (this.age == 11) {
            actor.removeItemFromInventory(this);
            System.out.println("The Power Star has disappeared from your inventory!");
        }
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        this.age += 1;
        if (this.age == 11) {
            currentLocation.removeItem(this);
            System.out.println("The Power Star has disappeared from the map.");
        }
    }

    /**
     * Check if the PowerStar has been paired with a ConsumeAction in the ConsumableItemManager and if the PowerStar
     * is being carried. If it has been paired, remove the ConsumeAction to ensure player cannot consume PowerStar
     * if it is on the ground (i.e. picking up the item then dropping it should remove the ConsumeAction).
     * Otherwise, if the PowerStar is simply being carried, add a new ConsumeAction.
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        if (TradableItemManager.getInstance().getConsumeAction(this)!= null
                && !this.hasCapability(Status.CARRIED)) {
            this.removeAction(TradableItemManager.getInstance().getConsumeAction(this));
        }
        else if (this.hasCapability(Status.CARRIED)){
            this.addAction(new ConsumeAction(this, Status.POWER_STAR));
        }
        return super.getAllowableActions();
    }

    /**
     * Interface method - getter for PowerStar's value.
     */
    @Override
    public int getValue() {
        return this.value;
    }
}