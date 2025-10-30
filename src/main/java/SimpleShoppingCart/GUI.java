package SimpleShoppingCart;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class GUI extends Application {
    private final ShoppingCart cart = new ShoppingCart();
    private final ListView<String> cartList = new ListView<>();
    private ResourceBundle bundle;
    private TextField countField;
    private TextField priceField;
    private Button addButton;
    private Button clearButton;
    private Label cartLabel;
    private ComboBox<String> langBox;
    private ComboBox<String> countryBox;
    private Label totalCost;

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

        countField = new TextField();
        priceField = new TextField();
        addButton = new Button();
        clearButton = new Button();
        cartLabel = new Label();
        totalCost = new Label();

        updateLocale();

        addButton.setOnAction(e -> {
            try {
                int count = Integer.parseInt(countField.getText());
                double price = Double.parseDouble(priceField.getText());
                cart.addItem(count, price);
                updateCartList(count, price);
                countField.clear();
                priceField.clear();
            } catch (NumberFormatException ex) {
                System.out.println("Error " + ex);
            }
        });

        clearButton.setOnAction(e -> {
            cart.clear();
            clearCartList();
        });

        VBox inputBox = new VBox(5, langBox, countryBox, countField, priceField, addButton, clearButton, totalCost);
        VBox mainBox = new VBox(10, inputBox, cartLabel, cartList);
        mainBox.setPadding(new Insets(10));

        stage.setScene(new Scene(mainBox, 300, 400));
        stage.setTitle("Tony Karlin");
        stage.show();
    }

    private void updateLocale() {
        Locale locale = new Locale(langBox.getValue(), countryBox.getValue());
        bundle = ResourceBundle.getBundle("MessageBundle", locale);

        countField.setPromptText(bundle.getString("count"));
        priceField.setPromptText(bundle.getString("price"));
        addButton.setText(bundle.getString("add"));
        clearButton.setText(bundle.getString("clear"));
        cartLabel.setText(bundle.getString("cart"));
    }


    private void clearCartList() {
        cartList.getItems().clear();
        double sum = 0.0;
        for (Item item : cart.getItems()) {
            cartList.getItems().add(item.getName() + " - $" + item.getCost());
        }
        totalCost.setText(bundle.getString("total") + " " +sum);
    }

    private void updateCartList(int quantity, double price) {
        cartList.getItems().clear();
        double sum = 0.0;
        for (Item item : cart.getItems()) {
            cartList.getItems().add(item.getName() + " - $" + item.getCost() + " (X" + quantity + " * $" + price + ")");
            sum += item.getCost();
        }
        totalCost.setText(bundle.getString("total") + " $" + String.format("%.2f", sum));
    }
}
