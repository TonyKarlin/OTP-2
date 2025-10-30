package SimpleShoppingCart;

public class Item {
    private final int index;
    private final int itemQuantity;
    private final double itemCost;

    public Item(int index, int itemName, double itemCost) {
        this.index = index;
        this.itemQuantity = itemName;
        this.itemCost = itemCost;
    }

    public int getIndex() {
        return index;
    }

    public double getCost() {
        return itemCost;
    }

    public int getQuantity() {
        return itemQuantity;
    }
}
