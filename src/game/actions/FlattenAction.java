package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Dirt;
import game.grounds.Jumpable;
import game.items.Coin;

import java.util.Random;

public class FlattenAction extends Action {
    //Private Attributes
    /**
     * The Ground that is to be attacked
     */
    private Jumpable target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public FlattenAction (Jumpable target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location targetLocation = this.getTargetLocation(actor, map);
        map.moveActor(actor, targetLocation);
        targetLocation.setGround(new Dirt());
        targetLocation.addItem(new Coin(targetLocation));
        String result = actor + " flattened " + this.target.getClass().getSimpleName() + ".";

        return result;

    }

    public Location getTargetLocation(Actor actor, GameMap map) {

        Location actorLocation = map.locationOf(actor);
        for (Exit exit: actorLocation.getExits()) {
            if (exit.getName() == this.direction) {
                return exit.getDestination();
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " flattens " + this.target.getClass().getSimpleName() + " at " + direction;
    }




}
