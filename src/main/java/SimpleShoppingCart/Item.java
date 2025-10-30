package SimpleShoppingCart;

public class Item {
    private final int itemName;
    private final double itemCost;

    public Item(int itemName, double itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public double getCost() {
        return itemCost;
    }

    public int getName() {
        return itemName;
    }
}
