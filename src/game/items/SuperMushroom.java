package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

import java.util.List;

/***
 * Things SuperMushroom are able to do/ what it does/ all the qualities SuperMushroom has
 * can be picked up
 * can be traded
 * can be dropped (dropped when koopa shell is atomized)
 * increases player's max HP by 50 (does not heal)
 * m -> M
 * 100% jump rate and no fall damage
 * M -> m, no 100% jump when player receives damage (if current hp < hp at start of tick)
 */
public class SuperMushroom extends Item {

    /**
     * Constructor
     *
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);

    }
}
