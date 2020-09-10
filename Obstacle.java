package sample;

import java.util.ArrayList;

/**
 * Obstacle
 * Any obstacle encountered by a pioneer along the Oregon Trail.
 *
 * @author N-Hagerdorn
 * @since 3/27/20
 */

public class Obstacle extends Landmark{

    private final double PENALTY = 0.75;
    private int weightLimit;

    /**
     * Create a new obstacle with a name, location, and a weight limit
     * @param name                 name of obstacle
     * @param location             location of obstacle
     * @param weightLimit          weight limit required to transverse obstacle
     */
    public Obstacle(String name, int location, int weightLimit) {
        super(name,location);
        this.weightLimit = weightLimit;
    }

    /**
     * returns a boolean specifying if the obstacle was crossed. If false, take 1/4 all items, and cross
     * @param playerItems            list of all the player's items
     * @return true if the weight of wagon is under the weight limit, false if not
     */
    public boolean crossObstacle(ArrayList<Item> playerItems) {
        int weight = 0;
        for (int i = 0; i < playerItems.size(); i++) {
            weight += playerItems.get(i).getQuantity();
        }

        if (weight <= weightLimit) {
            return true;
        }

        for (int i = 0; i < playerItems.size(); i++) {
            int amount = playerItems.get(i).getQuantity();
            playerItems.get(i).setQuantity((int) (amount * PENALTY));
        }
        return false;
    }

    /**
     * @return weight limit of obstacle
     */
    public int getWeightLimit() {
        return weightLimit;
    }
}
