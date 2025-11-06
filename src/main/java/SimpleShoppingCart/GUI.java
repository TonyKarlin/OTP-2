package SimpleShoppingCart;

import SimpleShoppingCart.Backend.ShoppingCartService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GUI extends Application {
    private final ShoppingCart cart = new ShoppingCart();
    Shop shop = new Shop();
    private final ListView<String> cartList = new ListView<>();
    private ComboBox<String> shopItemBox;
    private ResourceBundle bundle;
    private TextField countField;
    private TextField priceField;
    private Button addButton;
    private Button clearButton;
    private Label cartLabel;
    private ComboBox<String> langBox;
    private ComboBox<String> countryBox;
    private Label totalCost;
    private Button saveButton;

    @Override
    public void start(Stage stage) {
        langBox = new ComboBox<>();
        langBox.getItems().addAll("en", "fi", "ja", "sv");
        langBox.setValue("en");

        countryBox = new ComboBox<>();
        countryBox.getItems().addAll("US", "FI", "JP", "SE");
        countryBox.setValue("US");

        langBox.setOnAction(e -> updateLocale());
        countryBox.setOnAction(e -> updateLocale());

        shopItemBox = new ComboBox<>();
        shopItemBox.setPrefWidth(150);

        countField = new TextField();
        priceField = new TextField();
        addButton = new Button();
        clearButton = new Button();
        cartLabel = new Label();
        totalCost = new Label();
        saveButton = new Button();

        updateLocale();

        addItemToCart();

        clearButton.setOnAction(e -> {
            cart.clear();
            clearCartList();
        });

        saveToDatabase();

        VBox inputBox = new VBox(5, langBox, countryBox, shopItemBox, countField, priceField,
                addButton, clearButton, totalCost);
        VBox mainBox = new VBox(10, inputBox, cartLabel, cartList, saveButton);
        mainBox.setPadding(new Insets(10));

        stage.setScene(new Scene(mainBox, 300, 400));
        stage.setTitle("Tony Karlin");
        stage.show();
    }

    private void updateLocale() {
        int selectedIndex = shopItemBox.getSelectionModel().getSelectedIndex();

        Locale locale = new Locale(langBox.getValue(), countryBox.getValue());
        bundle = ResourceBundle.getBundle("MessageBundle", locale);

        List<String> localizedItems = new ArrayList<>();
        for (String item : shop.getListOfItems()) {
            localizedItems.add(bundle.getString(item.toLowerCase()));
        }
        shopItemBox.getItems().setAll(localizedItems);

        if (selectedIndex >= 0 && selectedIndex < shopItemBox.getItems().size()) {
            shopItemBox.getSelectionModel().select(selectedIndex);
        } else {
            shopItemBox.setValue(localizedItems.get(0));
        }

        countField.setPromptText(bundle.getString("count"));
        priceField.setPromptText(bundle.getString("price"));
        addButton.setText(bundle.getString("add"));
        clearButton.setText(bundle.getString("clear"));
        cartLabel.setText(bundle.getString("cart"));
        saveButton.setText(bundle.getString("save"));
    }

    private void addItemToCart() {
        addButton.setOnAction(e -> {
            try {
                int count = Integer.parseInt(countField.getText());
                double price = Double.parseDouble(priceField.getText());
                String itemName = shopItemBox.getValue();

                cart.addItem(itemName, count, price);
                updateCartList();
                countField.clear();
                priceField.clear();
            } catch (NumberFormatException ex) {
                System.out.println("Error " + ex);
            }
        });
    }

    private void saveToDatabase() {
        saveButton.setOnAction(e -> {
            ShoppingCartService dao = new ShoppingCartService();
            String lang = langBox.getValue();
            for (Item item : cart.getItems()) {
                try {
                    int itemId = dao.insertItem(item.getCost(), item.getQuantity());
                    dao.insertTranslation(itemId, lang, shopItemBox.getValue());
                } catch (SQLException ex) {
                    System.out.println("Error saving item: " + ex);
                }
            }
            System.out.println("Cart saved to database.");
        });
    }


    private void clearCartList() {
        cartList.getItems().clear();
        double sum = 0.0;
        for (Item item : cart.getItems()) {
            cartList.getItems().add(item.getQuantity() + " - $" + item.getCost());
        }
        totalCost.setText(bundle.getString("total") + " " +sum);
    }

    private void updateCartList() {
        cartList.getItems().clear();
        double sum = 0.0;
        for (Item item : cart.getItems()) {
            String name = item.getName();
            cartList.getItems().add(name + " - $" + item.getCost() + " (X" + item.getQuantity() +
                    " * $" + (item.getCost() / item.getQuantity()) + ")");
            sum += item.getCost();
        }
        totalCost.setText(bundle.getString("total") + " $" + String.format("%.2f", sum));
    }
}
