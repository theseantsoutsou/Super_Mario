package game.actors;

import game.Status;

/**
 * The FlyingKoopa class is a class that represents the enemy character Flying Koopa in Super Mario, the turtle with wings.
 * The FlyingKoopa class is subclass of the Koopa class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 3.0
 * @since 19-May-2022
 * @see Koopa
 */
public class FlyingKoopa extends Koopa{
    /**
     * Constructor for the FlyingKoopa class.
     * Calls its parent class Actor class's constructor to set name, display character, and HP attributes.
     * Adds the status FLY to its capability set
     *
     * @see Status#FLY
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        this.addCapability(Status.FLY);
    }

    /**
     * Interface method - stores FlyingKoopa's monologues as well as general Koopa monologues
     */
    @Override
    public void addToMonologues() {
        super.addToMonologues();
        this.getMonologues().add("Pam pam pam!");
    }
}
