package game.actors;

import game.Status;


public class FlyingKoopa extends Koopa{
    /**
     * Constructor for the FlyingKoopa class.
     * Calls its parent class Actor class's constructor to set name, display character, and HP attributes.
     * Loads new behaviours to its behaviours attribute in order of priority.
     * Adds a CAN_SLEEP status to its capabilities and a SuperMushroom to its inventory
     *
     * @see Koopa
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        this.addCapability(Status.FLY);

    }
    @Override
    public void addToMonologues() {
        this.getMonologues().add("Pam pam pam!");
    }
}
