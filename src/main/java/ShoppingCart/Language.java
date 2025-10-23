package ShoppingCart;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
    private final String language;
    private final String country;
    private static ResourceBundle rb = null;

    public Language(String language, String country) {
        this.language = language;
        this.country = country;
        Locale locale = new Locale(language, country);
        rb = ResourceBundle.getBundle("MessageBundle", locale);
    }

    public static ResourceBundle getRb() {
        return rb;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }
}
