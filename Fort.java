package sample;

import java.util.ArrayList;

/**
 * Fort
 * The forts along the Oregon Trail that pioneers would use to purchase supplies and rest in safety.
 *
 * @author N-Hagerdorn
 * @since 3/25/20
 */
public class Fort extends Landmark{
    private final int ITEM_LIMIT = 100;

    /**
     * Create a new fort with a name, location along the trail in miles, and given assortment of items to purchase.
     *
     * @param name          The name of the fort as a String.
     * @param location      The location of the fort as an int.
     */
    public Fort(String name, int location) {
        super(name,location);
    }

    /**
     * Purchase items from the fort. Checks whether the player has enough money and space to carry the item.
     *
     * @param money         The player's money as a double.
     * @param itemName      The name of the item the player wants to purchase as a String.
     * @param numItems      The number of items the player is purchasing as an int.
     * @param playerItems   The player's item storage to put the item in.
     * @return              The money the player has after the purchase.
     */
    public double buyItem(double money, String itemName, int numItems, ArrayList<Item> playerItems) {

        for (int i = 0; i < playerItems.size(); i++) {
            if (playerItems.get(i).getType().equals(itemName)) {
                Item itemBuying = playerItems.get(i);
                double cost = itemBuying.getPrice() * numItems;
                if (money >= cost) {
                    money -= cost;
                    itemBuying.setQuantity(itemBuying.getQuantity() + numItems);
                }
            }
        }
        return money;
    }
}
