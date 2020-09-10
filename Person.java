package sample;

import java.util.ArrayList;

/**
 * Person
 * One of the pioneers traveling to Oregon. Can be the player avatar or a family member traveling with the player.
 *
 * @author N-Hagerdorn
 * @since 3/25/20
 */
public class Person {
    private static final int MAX_HEALTH = 100;
    private static final int DISEASE_HEALTH = MAX_HEALTH / 4;
    private static int rations = 0;
    private String name;
    private String relation;
    private int health;
    private boolean isDiseased;

    /**
     * Create a new person with a name, relationship with the player avatar, and disease status.
     * @param name          The name of the person as a String.
     * @param relation      The person's relation to the player's avatar as a String
     * @param isDiseased    Whether the person has dysentery or not as a boolean.
     */
    public Person(String name, String relation, boolean isDiseased) {
        this.name = name;
        this.relation = relation;
        this.isDiseased = isDiseased;
        this.health = MAX_HEALTH;
        if (this.isDiseased) {
            this.health = DISEASE_HEALTH;
        }
    }

    /**
     * Get the currently set rations.
     * @return              The rations each person is allotted as an int.
     */
    public int getRations() { return rations; }

    /**
     * Get the name of the person.
     * @return              The name of the person as a String;
     */
    public String getName() { return this.name; }

    /**
     * Get the person's relation to the player avatar.
     * @return              The relation as a String.
     */
    public String getRelation() { return this.relation; }

    /**
     * Get the health of the person.
     * @return              The health of the person as an int.
     */
    public int getHealth() { return this.health; }

    /**
     * Get the disease status of the person.
     * @return              Whether the person has dysentery or not as a boolean.
     */
    public boolean getIsDiseased() { return this.isDiseased; }

    /**
     * Check whether the person is still alive.
     * @return              Whether the person is alive or not as a boolean.
     */
    public boolean isAlive() {
        if (this.health > 0) {
            return true;
        }
        return false;
    }

    /**
     * Set the rations each person is allotted.
     * @param rations       The rations each person is allotted as an int. Valid range is 1-3.
     */
    public void setRations(int rations) { this.rations = rations; }

    /**
     * Set the health of the person. Only works if the person is alive.
     * @param health        The person's health as an int.
     * @return              Whether the person's health was changed or not.
     */
    public boolean setHealth(int health) {
        if (this.isAlive()) {
            this.health = health;
            if (this.health < DISEASE_HEALTH) {
                this.isDiseased = true;
            }
            else {
                this.isDiseased = false;
            }
            return true;
        }
        return false;
    }

    /**
     * Use an item. This is mostly useful for eating food.
     * @param items
     * @param itemType
     * @return
     */
    public int useItem(ArrayList<Item> items, String itemType) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getType().equals(itemType)) {

            }
        }
    }
}
