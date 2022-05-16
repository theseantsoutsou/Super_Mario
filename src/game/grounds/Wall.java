package game.grounds;

import game.Status;

/**
 * The Wall class is a class that represents a generic brick wall.
 * The Wall class is a subclass of the Ground class and implements the Jumpable interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Wall extends HighGround {

	/**
	 * Constructor for the Wall class.
	 * Calls its parent class Ground class's constructor to set display character.
	 * Adds a HIGH_GROUND status to its capability.
	 *
	 * @see Status#HIGH_GROUND
	 */
	public Wall() {
		super('#', 80, 20);
	}

	/**
	 * Walls can block thrown objects
	 *
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
