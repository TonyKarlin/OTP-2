package SimpleShoppingCart;

import javafx.application.Application;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Application.launch(GUI.class, args);


//        try (Scanner sc = new Scanner(System.in)) {
//            System.out.println("Select a language: en, fi, ja, sv");
//            String language = sc.nextLine().toLowerCase();
//            System.out.println("Select a country: US, FI, JP, SE");
//            String country = sc.nextLine().toUpperCase();
//            Locale myLocale = new Locale(language, country);
//            ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle", myLocale);
//            ShoppingCart shoppingCart = new ShoppingCart();
//
//            while (true) {
//
//                System.out.println(bundle.getString("count"));
//                int itemCount = sc.nextInt();
//                System.out.println(bundle.getString("price"));
//                float itemPrice = sc.nextFloat();
//
//                shoppingCart.addItem(itemCount, itemPrice);
//                System.out.println(bundle.getString("total") + shoppingCart.getTotalCost());
//                System.out.println(bundle.getString("continue"));
//                if (sc.next().equals("N")) {
//                    System.out.println(bundle.getString("exit"));
//                    break;
//                }
//            }
//        }
    }
}
