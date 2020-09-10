package sample;

/**
 * Part extends Item
 * Wagon parts that a pioneer would keep on hand to replace broken parts.
 *
 * @author N-Hagerdorn
 * @since 3/25/20
 */
public class Part extends Item {
    private boolean isDamaged;

    /**
     * Create a new Part with a type, price, quantity, and damage status.
     * @param type          The type of part as a String.
     * @param price         The price of the part as an int.
     * @param quantity      The quantity of the part as an int.
     * @param isDamaged     The damage status of the part as a boolean.
     */
    public Part(String type, int price, int quantity, boolean isDamaged) {
        super(type, price, quantity);
        this.isDamaged = isDamaged;
    }

    /**
     * Get the damage status of the part.
     * @return              The damage status of the part as a boolean.
     */
    public boolean getIsDamaged() { return this.isDamaged; }

    /**
     * Set the damage status of the part.
     * @param isDamaged     The damage status of the part as a boolean.
     */
    public void setIsDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }
}
