package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.ArrayList;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

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

	@Override
	public String execute(Actor player, GameMap map) {
		int credits = Wallet.getInstance().getCredits();
		if (credits >= value) {
			Wallet.getInstance().modifyCredits(-1 * value);
			nonPlayerCharacter.removeItemFromInventory(item);
			player.addItemToInventory(item);
		} else {
			return "You have insufficient credits.";
		}
		return String.format("The player has bought %s for $%s.", item.toString(), value);
	}

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

	@Override
	public String menuDescription(Actor actor) {
		return String.format("Buy %s for $%s.", item.toString(), value);
	}

	public boolean setItem(Item item) {
		if (TradableItemInventory.getInstance().getTradableItems().contains(item)) {
			this.item = item;
			return true;
		} else {
			return false;
		}
	}
}

