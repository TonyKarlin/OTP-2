package SimpleShoppingCart;

public class Item {
    private final int index;
    private final String name;
    private final int itemQuantity;
    private final double itemCost;

    public Item(int index, String name, int itemName, double itemCost) {
        this.index = index;
        this.name = name;
        this.itemQuantity = itemName;
        this.itemCost = itemCost;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return itemCost;
    }

    public int getQuantity() {
        return itemQuantity;
    }
}
