package ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShoppingCart {
    Locale locale = Language.getLocale();
    private final ResourceBundle rb = ResourceBundle.getBundle("Localization.MessageBundle", locale);
    private final List<Item> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public List<Item> getCart() {
        return cartItems;
    }

    public void addItemToShoppingCart(Item item) {
        cartItems.add(item);
    }

    public boolean removeItemFromShoppingCart(int index) {
        return cartItems.remove(cartItems.get(index));
    }

    public void showShoppingCart() {
        System.out.println("\n" + rb.getString("your_cart") +"\n");
        int i = 1;
        for (Item item : cartItems) {
            System.out.println(i + ".");
            System.out.println(rb.getString("item_name") + ": " + item.getName() + "\n" +
                     rb.getString("item_quantity") + ": " + item.getQuantity() + "\n" +
                     rb.getString("item_total_price") + ": " + item.getTotalCost() + "€\n");
            i++;
        }
    }
}
