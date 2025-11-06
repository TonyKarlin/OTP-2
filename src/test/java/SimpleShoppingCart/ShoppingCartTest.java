package SimpleShoppingCart;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    void addItem() {
        cart.addItem("Banana", 2, 10.0);
        cart.addItem("Fish", 5, 1.50);
        List<Item> items = cart.getItems();
        assertEquals(2, items.size());
        assertEquals(20.0, items.get(0).getCost());
        assertEquals(7.0, items.get(1).getCost());
        assertEquals(27.0, cart.getTotalCost());
    }

    @Test
    void clear() {
        cart.addItem("Banana", 2, 10.0);
        cart.addItem("Fish", 5, 1.50);
        List<Item> items = cart.getItems();
        assertEquals(2, items.size());
        assertEquals(27.0, cart.getTotalCost());
        cart.clear();
        items = cart.getItems();
        assertEquals(0, items.size());
        assertEquals(0, cart.getTotalCost());
    }

    @Test
    void getItems() {
        cart.addItem("Banana", 2, 10.0);
        cart.addItem("Fish", 5, 1.50);
        List<Item> items = cart.getItems();
        assertNotNull(items);
        assertEquals(2, items.size());
    }

    @Test
    void getTotalCost() {
        cart.addItem("Banana", 2, 10.0);
        cart.addItem("Fish", 5, 1.50);
        assertEquals(27.0, cart.getTotalCost());

    }
}