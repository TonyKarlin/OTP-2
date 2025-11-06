package SimpleShoppingCart;

import java.util.ResourceBundle;

public class Shop {
    private final String[] listOfItems;

    public Shop() {
        this.listOfItems = new String[]{"banana", "orange", "apple", "fish", "meat", "vegetables"};
    }

    public String[] getListOfItems() {
        return listOfItems;
    }
}
