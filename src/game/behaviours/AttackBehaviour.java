package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.BasicAttackAction;
import game.actions.FirePunchAction;

/**
 * The AttackBehaviour class is a class that allows for automated attacks from an actor to another.
 * The AttackBehaviour class implements the Behaviour interface.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Creates an AttackAction if target within reach of actor.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an AttackAction if target is hostile and on ground level; null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        for (Exit exit: location.getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target != null && target.hasCapability(Status.HOSTILE_TO_ENEMY)
                    && (!target.hasCapability(Status.ON_HIGH_GROUND) || actor.hasCapability(Status.FLY))) {
                if (actor.hasCapability(Status.FIRE_ATTACK)) {
                    return new FirePunchAction(target, exit.getName());
                }
                else {
                    return new BasicAttackAction(target, exit.getName());
                }
            }
        }
        return null;
    }

}