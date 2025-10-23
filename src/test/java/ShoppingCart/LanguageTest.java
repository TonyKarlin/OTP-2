package ShoppingCart;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class LanguageTest {

    @Test
    void getRb() {
        new Language("fi", "FI");
        assertNotNull(Language.getRb());

    }

    @Test
    void getCountry() {
        Language lang = new Language("fi", "FI");
        assertEquals("FI", lang.getCountry());
    }

    @Test
    void getLanguage() {
        Language lang = new Language("fi", "FI");
        assertEquals("fi", lang.getLanguage());
    }
}