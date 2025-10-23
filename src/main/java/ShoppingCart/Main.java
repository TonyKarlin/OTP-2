package ShoppingCart;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Shop shop = new Shop();
        ShoppingCart cart = new ShoppingCart();

        System.out.print("Enter language: ");
        String lang = sc.nextLine();
        System.out.print("Enter country: ");
        String country = sc.nextLine();

        System.out.println("Selected language: " + lang + " " + country.toUpperCase());

        new Language(lang, country.toUpperCase());
        Locale locale = Language.getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("Localization.MessageBundle", locale);

        shop.printList();
        System.out.println("\n" + rb.getString("initial_amount"));
        int count = sc.nextInt();

        if (count > 0) {
            int i = 1;
            System.out.println("\n" + rb.getString("add_instruction") +
                    " " + count + " " + rb.getString("instruction"));
            while (i <= count) {
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("q")) break;
                if (i != 1) shop.printList();

                String item = shop.selectItemToBuy(sc);
                int quantityOfItems = shop.setAmountOfItemsToBuy(sc);
                double itemPrice = shop.setPriceOfItem(sc);
                cart.addItemToShoppingCart(new Item(item, quantityOfItems, itemPrice));
                i++;
            }

            cart.showShoppingCart();


            while (!cart.getCart().isEmpty()) {
                System.out.print("\n" + rb.getString("position_input") + ": ");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("q")) break;

                try {
                    int item = Integer.parseInt(input);
                    if (cart.removeItemFromShoppingCart(item - 1)) {
                        System.out.println(rb.getString("removal_success"));
                    } else {
                        System.out.println(rb.getString("removal_failure"));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(rb.getString("removal_exception"));
                }
            }
            sc.close();

            double totalCost = 0;
            if (!cart.getCart().isEmpty()) {
                for (Item item : cart.getCart()) {
                    totalCost += item.getTotalCost();
                }
                System.out.println("\n" + rb.getString("total_cart_price") + totalCost + "€");
            } else {
                System.out.println(rb.getString("empty_cart"));
            }
        } else {
            System.out.println(rb.getString("no_items"));
        }
    }
}
