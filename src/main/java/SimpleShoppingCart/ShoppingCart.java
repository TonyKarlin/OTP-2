package SimpleShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int totalCost;
    private final ArrayList<Item> cartItems;
    private int index = 1;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
        this.totalCost = 0;
    }

    public void addItem(int count, double pricePer) {
        int cost = (int) (count * pricePer);
        totalCost += cost;
        this.cartItems.add(new Item(index, count, cost));
        index++;
    }

    public void clear() {
        cartItems.clear();
        totalCost = 0;
        index = 1;
    }

    public List<Item> getItems() {
        return new ArrayList<>(cartItems);
    }

    public double getTotalCost() {
        return this.totalCost;
    }
}
