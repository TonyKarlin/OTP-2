package ShoppingCart;

import java.util.Locale;

public class Language {
    private final String language;
    private final String country;
    private static Locale locale;

    public Language(String language, String country) {
        this.language = language;
        this.country = country;
        locale = new Locale(language, country);
    }

    public static Locale getLocale() {
        return locale;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }
}
