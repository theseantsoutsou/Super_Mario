package game.grounds;

/**
 * Interface for high ground terrain that retrieves the success rate and the fall damage of a specific
 * Ground object
 */
public interface Jumpable {

    int getSuccessRate();
    int getFallDamage();

}
