package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * The Lava class is a class that represents a blazing fire ground.
 * The Lava class is a subclass of the Ground class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 15-May-2022
 */
public class Lava extends Ground {
    //Private attributes
    private int damage = 15;

    /**
     * Constructor for the Lava class
     * Calls its parent class's constructor to set the display character.
     */
    public Lava() {
        super('L');
    }

    /**
     * Ground can also experience the joy of time.
     * Checks if there is an actor on this object. If yes, deal damage and check if the actor is still conscious.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            location.getActor().hurt(damage);
            System.out.println(location.getActor() + " is burned by lava!");
            if (!location.getActor().isConscious()) {
                location.map().removeActor(location.getActor());
            }
        }
    }

    /**
     * Check if actor to-enter is a player.
     *
     * @param actor the Actor to check
     * @return true if actor is a friendly player; false if the actor is an enemy
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }


}
