package game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;

import java.util.Map;
import java.util.TreeMap;

public abstract class Enemy extends Actor {
    //Private attribute
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public Map<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.ATTACKED) || this.hasCapability(Status.GOT_ATTACKED)) {
            Location here = map.locationOf(this);
            for(Exit exit: here.getExits()) {
                Actor target = exit.getDestination().getActor();
                if (target != null && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    this.behaviours.put(2, new FollowBehaviour(target));
                }
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }
}
