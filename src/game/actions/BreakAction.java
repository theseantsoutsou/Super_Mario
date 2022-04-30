package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.npcs.Koopa;

/**
 * Special Action for attacking other Actors.
 */
public class BreakAction extends Action {

    /**
     * The Koopa Shell that is to be broken
     */
    private Koopa target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public BreakAction(Koopa target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        if (actor.hasCapability(Status.POWER_STAR) && !this.target.isDormant()) {
            this.target.makeDormant();
            return this.target + " was instakilled";
        }

        String result = null;
        int damage = weapon.damage();

        if (this.target.isDormant()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result = actor + " broke Koopa's shell";
            result += System.lineSeparator() + target + " is killed";

        } else {
            this.target.hurt(damage);
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        if (target.isDormant()) {
            return actor + " break Koopa's shell at " + direction;
        } else {
            return actor + " attacks " + target + " at " + direction;
        }

    }
}
