package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Wall extends Ground implements Jumpable{
	//Private Attributes
	private static final int JUMP_SUCCESS_RATE = 80;
	private static final int FALL_DAMAGE = 20;

	public Wall() {
		super('#');
	}

	public int getSuccessRate() {
		return JUMP_SUCCESS_RATE;
	}

	public int getFallDamage() {
		return FALL_DAMAGE;
	}

	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction){
		ActionList actions = new ActionList();

		Boolean sameGround = location.map().locationOf(actor).equals(location);

		if(actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !sameGround) {
			actions.add(new JumpAction(this, direction));
		}

		return actions;
	}
}
