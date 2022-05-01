package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * The Dirt class is a class that represents bare dirt
 * The Dirt class is a subclass of the Ground class
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Dirt extends Ground {

	/**
	 * Constructor for the Dirt class.
	 * Calls its parent class Ground class's constructor to set display character.
	 * Adds a FERTILE status to its capability.
	 *
	 * @see Status#FERTILE
	 */
	public Dirt() {
		super('.');
		this.addCapability(Status.FERTILE);
	}
}
