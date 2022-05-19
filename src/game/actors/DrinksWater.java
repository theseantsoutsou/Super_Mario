package game.actors;

public interface DrinksWater {
    default void registerDrinks(){
        ActorManager.getInstance().addToBaseAttackList(this);
    }
    void updateBaseAttack(int value);
    int getBaseAttack();
}
