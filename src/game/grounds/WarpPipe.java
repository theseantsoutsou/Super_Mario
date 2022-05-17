package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actions.WarpAction;
import game.npcs.PiranhaPlant;

public class WarpPipe extends HighGround implements Spawnable, Teleport{
    //Private Attribute
    private int age;
    private Location destination;

    /**
     * Constructor.
     */
    public WarpPipe() {
        super('C', 100, 0);
        this.addToTeleportManager();
        this.age = 1;
    }

    /**
     * Ground can also experience the joy of time.
     * Warp Pipes will 'spawn' a piranha plant ON the 2nd turn,
     *
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        //this.age++; COMMENTED OUT FOR TESTING///////////////////////////////////////////
        if (this.age == 2) {
            this.spawn(location);
            this.addCapability(Status.PIRANHA);
        }
        if (!location.containsAnActor()) {
            this.removeCapability(Status.PIRANHA);
        }
    }

    /**
     * Interface method - WarpPipes spawn a Piranha Plant on the second turn
     *
     * @param location the location of the Tree
     * @see PiranhaPlant
     */
    @Override
    public void spawn(Location location) {
        location.addActor(new PiranhaPlant());
    }

    /**
     * WarpPipes can block thrown objects
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Returns an ActionList which content depends on the actor's capabilities.
     * Allows an actor to jump onto the WarpPipe or warp through it.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new collection of Actions
     * @see Status
     * @see JumpAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        boolean sameGround = location.map().locationOf(actor).equals(location);
        if (!this.hasCapability(Status.PIRANHA)) {
            if (!sameGround) {
                actions.add(new JumpAction(this, direction));
            }
            else {
                if(destination==null) {
                    destination = TeleportManager.getGameMaps().get("lavaZone").at(0, 0);
                }
                actions.add(new WarpAction(destination));
                TeleportManager.getWarpPipe(destination.getGround()).setDestination(location);
            }
        }
        return actions;
    }
    @Override
    public void setDestination(Location destination){
        this.destination = destination;
    }
}
