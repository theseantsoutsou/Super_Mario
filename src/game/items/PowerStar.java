package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
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

    private ConsumeAction action = new ConsumeAction(this, Status.POWER_STAR);
    private int value = 600;
    private int age;

    /**
     * Constructor for PowerStar - takes in boolean portable, which sets the portability of the item.
     * (any traded item should not be portable)
     * @param portable
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
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        this.age += 1;
        if (this.age == 10) {
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        this.age += 1;
        if (this.age == 10) {
            currentLocation.removeItem(this);
        }
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
        if(super.getAllowableActions().contains(this.action)) {
            this.removeAction(this.action);
        }
        if (this.hasCapability(Status.CARRIED)){
            this.addAction(this.action);
        }
        return super.getAllowableActions();
    }
    @Override
    /**
     * Getter
     *
     * TradeableItem interface method - get the value of the item.
     */
    public int getValue() {
        return this.value;
    }
}