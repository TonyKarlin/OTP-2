package ShoppingCart;

public class Item {
    private final String name;
    private final double price;
    private final int quantity;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalCost() {
        return quantity * price;
    }
}
