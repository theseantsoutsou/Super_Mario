package game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;

public class PiranhaPlant extends Enemy implements Resettable {
    /**
     * Constructor.
     *
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.getBehaviours().put(1, new AttackBehaviour());
        this.registerInstance();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : this.getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    /**
     * Interface method - Increase and rest Piranha Plant HP upon reset.
     *
     * @param map The GameMap which this object exists in.
     */
    @Override
    public void resetInstance(GameMap map) {
        this.resetMaxHp(this.getMaxHp() + 50);
    }
}
