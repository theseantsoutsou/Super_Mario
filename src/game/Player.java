package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.items.Wallet;

/**
 * Class representing the Player.
 */
public class Player extends Actor  {

	private final Menu menu = new Menu();
	int invincibleTurns = 0;


	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.TRADE);
		this.addCapability(Status.CONVERSES);
	}

	@Override
	public void hurt(int points) {
		if (this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
		}
		super.hurt(points);
	}

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

		// print player hp
		System.out.println(this.printHp() + " " + Wallet.getInstance().printCredits());
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void addItemToInventory(Item item) {
		item.addCapability(Status.CARRIED);
		super.addItemToInventory(item);
	}

	@Override
	public void removeItemFromInventory(Item item) {
		item.removeCapability(Status.CARRIED);
		super.removeItemFromInventory(item);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}
}
