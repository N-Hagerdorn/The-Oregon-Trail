package sample;

/**
 * Item
 * The items a pioneer can take with them on the journey to Oregon.
 *
 * @author N-Hagerdorn
 * @since 3/25/20
 */
public class Item {
    private String type;
    private double price;
    private int quantity;

    /**
     * Create a new item with a type, price, and quantity.
     * @param type          The type of item as a String.
     * @param price         The price of the item as a double.
     * @param quantity      The quantity of the item as an int.
     */
    public Item(String type, double price, int quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Get the type of item.
     * @return              The type of the item as a String.
     */
    public String getType() { return this.type; }

    /**
     * Get the price of a single unit of the item.
     * @return              The price of the item as a double.
     */
    public double getPrice() { return this.price; }

    /**
     * Get the quantity of the item.
     * @return              The quantity of the item as an int.
     */
    public int getQuantity() { return this.quantity; }

    /**
     * Set the quantity of the item.
     * @param quantity      The quantity to set the item to.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Use a certain number of this item.
     * @param numItems      The number of items to use as an int.
     * @return              The number of items left after using some.
     */
    public int useItem(int numItems) {
        if (this.quantity > numItems) {
            this.quantity -= numItems;
            return this.quantity;
        }
        return -1;
    }
}
