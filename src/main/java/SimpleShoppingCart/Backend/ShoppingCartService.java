package SimpleShoppingCart.Backend;

import java.sql.*;

public class ShoppingCartService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shopping_cart_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public int insertItem(double price, int quantity) throws SQLException {
        String query = "INSERT INTO items (price, quantity) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, price);
            ps.setInt(2, quantity);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public void insertTranslation(int itemId, String lang, String name) throws SQLException {
        String query = "INSERT INTO item_translations (item_id, lang, name) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, itemId);
            ps.setString(2, lang);
            ps.setString(3, name);
            ps.executeUpdate();
        }
    }

    public String getItemName(int itemId, String lang) throws SQLException {
        String query = "SELECT name FROM item_translations WHERE item_id = ? AND lang = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, itemId);
            ps.setString(2, lang);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("name");
        }
        return null;
    }
}
