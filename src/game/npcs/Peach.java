package game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.RescueAction;

/**
 * The Peach class is a class that represents the non-playable character Peach in Super Mario.
 * The Peach class is subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Peach extends Actor {

    /**
     * Constructor.
     */
    public Peach() {
        super("Peach", 'P', 100);
        this.addCapability(Status.HOSTAGE);
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HERO)) {
            actions.add(new RescueAction(this, direction));
        }

        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.hasCapability(Status.HOSTAGE)) {

        }
        return new DoNothingAction();
    }
}
