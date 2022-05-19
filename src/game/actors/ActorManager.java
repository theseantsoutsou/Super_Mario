package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

public class ActorManager {
    private static ActorManager instance;

    public DrinksWater getActorThatDrinks(Actor actor) {
        int index = this.hasBaseAttack.indexOf(actor);
        if(index>=0) {return this.hasBaseAttack.get(index);}
        return null;
    }

    public Speaks getActorThatSpeaks(Actor actor) {
        int index = this.actorThatSpeaks.indexOf(actor);
        if(index>=0) {return this.actorThatSpeaks.get(index);}
        return null;
    }

    private ArrayList<DrinksWater> hasBaseAttack = new ArrayList<DrinksWater>();
    private ArrayList<Speaks> actorThatSpeaks = new ArrayList<Speaks>();

    public void addToBaseAttackList(DrinksWater actor){
        hasBaseAttack.add(actor);
    }

    public void addToSpeakingActorsList(Speaks actor){
        actorThatSpeaks.add(actor);
    }

    public static ActorManager getInstance(){
        if(instance==null){
            instance=new ActorManager();
        }
        return instance;
    }
}
