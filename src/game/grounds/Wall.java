package game.grounds;

import game.Status;

/**
 * The Wall class is a class that represents a generic brick wall.
 * The Wall class is a subclass of the HighGround class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 02-May-2022
 */
public class Wall extends HighGround {

	/**
	 * Constructor for the Wall class.
	 * Calls its parent class's constructor to set display character, jump success rate, and fall damage.
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
