package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * Key class that extends Item and is used to add the capability HERO to the player.
 * @author Sean Tsou
 * @since 15/05/22
 */

public class Key extends Item {
    /***
     * Constructor.
     */
    public Key() {
        super("Key", 'k', true);
        this.addCapability(Status.HERO);
    }
}
