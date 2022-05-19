package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.actions.ResetAction;
import game.items.Bottle;
import game.items.Wallet;


/**
 * The Player class is a class that represents a player.
 * The Player class is subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Player extends Actor implements Resettable, DrinksWater {
	//Private attributes
	private final Menu menu = new Menu();
	private int invincibleTurns = 0;
	private int fireTurns = 0;

	private int baseAttack = 0;

	/**
	 * Constructor for the Player class.
	 * Calls its parent class Actor class's constructor to set name, display character, and HP attributes.
	 * Adds the HOSTILE_TO_ENEMY, TRADE, and CONVERSES statuses to its capabilities.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 * @see Status#HOSTILE_TO_ENEMY
	 * @see Status#TRADE
	 * @see Status#CONVERSES
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.TRADE);
		this.addCapability(Status.CONVERSES);
		this.addCapability(Status.RESETTABLE);
		this.addCapability(Status.MARIO);
		this.addItemToInventory(new Bottle());
		this.registerDrinks();
		this.registerInstance();
	}

	/**
	 * Do some damage to the Player.
	 * If the player is hurt while under the effects of SuperMushroom, remove its effects.
	 * If the Player's hitpoints go down to zero, it's game over.
	 *
	 * @param points number of hitpoints to deduct.
	 * @see Actor#hurt(int points)
	 */
	@Override
	public void hurt(int points) {
		if (this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
		}
		super.hurt(points);
	}

	/**
	 * Select and return an action to perform on the current turn.
	 * Modifies the Player's ON_HIGH_GROUND status based on player's current location
	 * Check if Player has POWER_STAR status and how many turns it has been in effect; remove if necessary
	 * Prints Player's HP and show the menu of actions Player can perform on the console
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 * @see Status#ON_HIGH_GROUND
	 * @see Status#POWER_STAR
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (!map.locationOf(this).getGround().hasCapability(Status.HIGH_GROUND) &&
				this.hasCapability(Status.ON_HIGH_GROUND)) {
			this.removeCapability(Status.ON_HIGH_GROUND);
		}

		if (this.hasCapability(Status.POWER_STAR)) {
			this.invincibleTurns += 1;
		}

		if (this.invincibleTurns == 10) {
			this.removeCapability(Status.POWER_STAR);
			this.invincibleTurns += 1;
		}
		else if (this.invincibleTurns == 11) {
			System.out.println(this + " is no longer invincible");
			this.invincibleTurns = 0;
		}

		//fire attack counter
		if (this.hasCapability(Status.FIRE_ATTACK)) {
			this.fireTurns +=1;
		}
		if (this.fireTurns == 20){
			this.removeCapability(Status.FIRE_ATTACK);
			this.fireTurns +=1;
		}
		else if (this.fireTurns == 21){
			System.out.println(this + " can no longer attack with fire");
			this.fireTurns = 0;
		}


		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if(this.hasCapability(Status.RESETTABLE)){
			actions.add(new ResetAction());
		}
		// print player hp
		System.out.println(this.printHp() + " " + Wallet.getInstance().printCredits());
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Give an item the status of CARRIED then add it to Player's inventory.
	 *
	 * @param item The Item to add.
	 * @see Status#CARRIED
	 */
	@Override
	public void addItemToInventory(Item item) {
		item.addCapability(Status.CARRIED);
		super.addItemToInventory(item);
	}

	/**
	 * Remove an item's status of CARRIED then remove it from Player's inventory.
	 *
	 * @param item The Item to remove.
	 */
	@Override
	public void removeItemFromInventory(Item item) {
		item.removeCapability(Status.CARRIED);
		super.removeItemFromInventory(item);
	}

	/**
	 * Get the display character of the Player.
	 *
	 * @return the uppercase displayChar of the Player if it has the status TALL; lowercase otherwise
	 * @see	Status#TALL
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Interface method - Resets the capabilities give to the player by Magical Items when game is reset.
	 *
	 * @param map The GameMap which this object exists in.
	 */
	@Override
	public void resetInstance(GameMap map) {
		this.removeCapability(Status.TALL);
		this.removeCapability(Status.POWER_STAR);
		this.removeCapability(Status.FIRE_ATTACK);
		this.heal(this.getMaxHp());
	}
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(5 + this.getBaseAttack(), "punches");
	}

	@Override
	public void updateBaseAttack(int value) {
		baseAttack+=value;
	}

	@Override
	public int getBaseAttack() {
		return baseAttack;
	}
}
