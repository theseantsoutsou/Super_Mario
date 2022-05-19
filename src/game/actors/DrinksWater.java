package game.actors;

public interface DrinksWater {
    default void addToEnhancementsManager(){
        ActorManager.getInstance().addToBaseAttackList(this);
    }
    void updateBaseAttack(int value);
    int getBaseAttack();
}
