package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.ResetAction;
import game.items.Wallet;

/**
 * Class representing the Player.
 */
public class Player extends Actor  {

	private final Menu menu = new Menu();
	int invincibleTurns = 0;


	/**
	 * Constructor.
	 * Player has capabilties of being hostile(attacking), trading, speaking and resetting the game
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.TRADE);
		this.addCapability(Status.CONVERSES);
		this.addCapability(Status.RESETTABLE);
	}

	/**
	 * Determining whether the player has been 'hurt' in order to remove the TALL capability (superMushroom)
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		if (this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
		}
		super.hurt(points);
	}

	/**
	 * Determines what actions the player can make during their turn
	 * This method keeps track of the number of rounds the player has been invincible after
	 * using a power star
	 * Displays the player's hp and walleton the UI
	 * Allows the user to reset the game once per life
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (this.invincibleTurns == 10) {
			this.removeCapability(Status.POWER_STAR);
			System.out.println(this + " is no longer invincible");
			this.invincibleTurns = 0;
		}
		else if (this.hasCapability(Status.POWER_STAR)) {
			this.invincibleTurns += 1;
		}
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		//resetting the game
		if (this.hasCapability(Status.RESETTABLE)){
			ResetAction resetAction = new ResetAction(this);
			resetAction.execute(this,map);
			this.resetInstance();
		}


		// print player hp
		System.out.println(this.printHp() + " " + Wallet.getInstance().printCredits());
		// return/print the console menu
		return menu.showMenu(this, actions, display);


	}

	/**
	 * Adds an item to the player's inventory
	 * @param item The Item to add.
	 */
	@Override
	public void addItemToInventory(Item item) {
		item.addCapability(Status.CARRIED);
		super.addItemToInventory(item);
	}

	/**
	 * Removes an item from the player's inventory
	 * @param item The Item to remove.
	 */
	@Override
	public void removeItemFromInventory(Item item) {
		item.removeCapability(Status.CARRIED);
		super.removeItemFromInventory(item);
	}

	/**
	 * Changes the display character from m -> M if the player has the capability TALL (consumed a super mushroom)
	 * @return
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}


	/**
	 * temporary way restore the player's health and remove certain capabilities after reset
	 */
	public void resetInstance(){
		this.heal(getMaxHp());
		this.removeCapability(Status.POWER_STAR);
		this.removeCapability(Status.TALL);

	}
}
