package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Fire Item which is spawned during FireAttackAction. Hurts actor at its location.
 */
public class Fire extends Item {
    /**
     * Used to track the amount of turns since the object was instantiated.
     */
    private int age;
    /**
     *
     * Damage dealt to Actor
     */
    private int damage = 20;

    /**
     * constructor
     */
    public Fire() {
        super("Fire", 'v', false);
        this.age = 0;
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        this.age++;
        if (this.age == 4) {
            currentLocation.removeItem(this);
        }
        if (currentLocation.containsAnActor()) {
            Actor target = currentLocation.getActor();
            target.hurt(this.damage);
            System.out.println(target + " burns!");
            if (!currentLocation.getActor().isConscious()) {
                currentLocation.map().removeActor(currentLocation.getActor());
            }
        }
    }
}

