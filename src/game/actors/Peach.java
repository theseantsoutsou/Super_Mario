package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.RescueAction;
import game.behaviours.Behaviour;
import game.behaviours.SpeechBehaviour;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Peach class is a class that represents the non-playable character Peach in Super Mario.
 * The Peach class is subclass of the Actor class.
 *
 * @author Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Peach extends Actor implements Speaks{
    ArrayList<String> monologues = new ArrayList<>();
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();
    /**
     * Constructor.
     */
    public Peach() {
        super("Peach", 'P', 100);
        this.addCapability(Status.HOSTAGE);
        this.behaviours.put(1, new SpeechBehaviour(this));
        this.registerSpeech();
        this.addToMonologues();
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
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public ArrayList<String> getMonologues() {
        return monologues;
    }

    @Override
    public void addToMonologues() {
        monologues.add("Dear Mario, I'll be waiting for you...");
        monologues.add("Never gonna give you up!");
        monologues.add("Release me, or I will kick you!");
    }
}
