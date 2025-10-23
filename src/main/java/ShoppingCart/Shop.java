package ShoppingCart;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Shop {
    Locale locale = Language.getLocale();
    private final ResourceBundle rb = ResourceBundle.getBundle("Localization.MessageBundle", locale);
    private final String[] listOfItems = {rb.getString("banana"), rb.getString("orange"),
            rb.getString("apple"), rb.getString("fish"),
            rb.getString("meat"), rb.getString("vegetables"),
            rb.getString("eggs"), rb.getString("candy"), rb.getString("bread")};


    public String[] getListOfItems() {
        return listOfItems;
    }


    public String selectItemToBuy(Scanner sc) {
        System.out.print("\n" + rb.getString("item_selection"));
        if (!sc.hasNextInt()) {
            sc.nextLine();
            throw new IllegalArgumentException(rb.getString("item_selection_exception"));
        }
        int index = sc.nextInt();
        return listOfItems[index];
    }

    public int setAmountOfItemsToBuy(Scanner sc) {
        System.out.print("\n" + rb.getString("item_amount") + " ");
        if (!sc.hasNextInt()) {
            sc.nextLine();
            throw new IllegalArgumentException(rb.getString("item_amount_exception"));
        }
        return sc.nextInt();
    }

    public double setPriceOfItem(Scanner sc) {
        System.out.print("\n" + rb.getString("item_price"));
        if (!sc.hasNextDouble()) {
            sc.nextLine();
            throw new IllegalArgumentException(rb.getString("item_price_exception"));
        }
        return sc.nextDouble();
    }

    public void printList() {
        for (int i = 1; i < listOfItems.length; i++) {
            System.out.println(i + ". " + listOfItems[i - 1]);
        }
    }
}
