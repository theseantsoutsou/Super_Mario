package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.ArrayList;

import game.items.TradableItem;
import game.items.TradableItemInventory;
import game.items.Wallet;

/**
 * Special Action for attacking other Actors.
 */
public class TradeAction extends Action {
	/**
	 * The Actor engaged in trade.
	 */
	protected Actor nonPlayerCharacter;
	protected Item item;
	private int value;

	/**
	 * Constructor.
	 *
	 * @param item the Item to be traded
	 *             Verifies is item is a TradableItem.
	 *             Retrieves value of item.
	 *             Sets nonPlayerCharacter.
	 */
	private TradeAction(Item item, Actor NPC) throws Exception {
		if (setItem(item)) {
			this.nonPlayerCharacter = NPC;
			ArrayList<TradableItem> inventory = TradableItemInventory.getInstance().getTradableItems();
			this.value = inventory.get(inventory.indexOf(item)).getValue();
		} else {
			throw new Exception("Attempting to trade non-tradable item");
		}
	}

	/**
	 * Determines if the player has enough coins in their wallet in order to purchase the item
	 * they want from Toad
	 * @param player
	 * @param map    The map the actor is on.
	 * @return
	 */
	@Override
	public String execute(Actor player, GameMap map) {
		int credits = Wallet.getInstance().getCredits();
		if (credits >= value) {
			nonPlayerCharacter.removeItemFromInventory(item);
			Wallet.getInstance().modifyCredits(-1 * value);
			player.addItemToInventory(item);
		} else {
			return "You have insufficient credits.";
		}
		return String.format("The player has bought %s for $%s.", this.item, value);
	}

	/**
	 * Allows the player to trade with the target (toad)
	 * @param target
	 * @return
	 */
	public static ActionList getTradeActions(Actor target) {
		ActionList tradeActions = new ActionList();
		Action action = null;
		for (Item i : target.getInventory()) {
			try {
				tradeActions.add(new TradeAction(i, target));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return tradeActions;

	}

	/**
	 * Display the description to trade an item on the menu
	 * @param actor The actor performing the action.
	 * @return
	 */
	@Override
	public String menuDescription(Actor actor) {
		return String.format("Buy %s for $%s.", item.toString(), value);
	}

	/**
	 * Sets an Item that will be for trade
	 * @param item
	 * @return
	 */
	public boolean setItem(Item item) {
		if (TradableItemInventory.getInstance().getTradableItems().contains(item)) {
			this.item = item;
			return true;
		} else {
			return false;
		}
	}
}

