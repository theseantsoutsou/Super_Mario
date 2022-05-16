package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.ArrayList;

import game.items.TradableItem;
import game.items.TradableItemManager;
import game.items.Wallet;

/**
 * The TradeAction class is a special Action for an actor to trade with Toad
 * The TradeAction class is a subclass of the Action class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
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
	 * Verifies item is a TradableItem.
	 * Retrieves value of item.
	 * Sets nonPlayerCharacter.
	 *
	 * @param item the Item to be traded
	 * @param NPC the actor in charge of the trade
	 */
	private TradeAction(Item item, Actor NPC) throws Exception {
		if (setItem(item)) {
			this.nonPlayerCharacter = NPC;
			ArrayList<TradableItem> inventory = TradableItemManager.getInstance().getTradableItems();
			this.value = inventory.get(inventory.indexOf(item)).getValue();
		} else {
			throw new Exception("Attempting to trade non-tradable item");
		}
	}

	/**
	 * Executes TradeAction
	 * Determines if the player has enough coins in their wallet in order to purchase the item they want from Toad
	 *
	 * @param player the player involved in the trade
	 * @param map    The map the actor is on.
	 * @return A String describing the outcome of the trade
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
	 *
	 * @param target actor to trade with
	 * @return a new list of Actions containing possible TradeActions
	 */
	public static ActionList getTradeActions(Actor target) {
		ActionList tradeActions = new ActionList();
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
	 * Display the description to trade an item on the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return A String to be added to actor's menu of options.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return String.format("Buy %s for $%s.", item.toString(), value);
	}

	/**
	 * Sets an Item that will be for trade, by checking if the static TradableItemInventory contains the item.
	 *
	 * @param item the item to be traded
	 * @return true the item has been set, false otherwise
	 * @see TradableItemManager
	 */
	public boolean setItem(Item item) {
		if (TradableItemManager.getInstance().getTradableItems().contains(item)) {
			this.item = item;
			return true;
		} else {
			return false;
		}
	}
}

