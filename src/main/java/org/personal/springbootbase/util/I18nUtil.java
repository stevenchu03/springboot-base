package org.personal.springbootbase.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class I18nUtil {

    private static final String I18N_RESOURCE_PATH = "i18n";
    private static final Locale I18N_DEFAULT_LOCALE = Locale.ENGLISH;

    public static String translate(String key, Object... args) {
        return translate(I18N_DEFAULT_LOCALE, key, args);
    }

    public static String translate(Locale locale, String key, Object... args) {
        String message = getMessageStringFromResource(locale, key);
        if (message == null) {
            message = key;
        }

        return String.format(message, args);
    }

    private static String getMessageStringFromResource(Locale locale, String key) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(I18N_RESOURCE_PATH, locale);
            return bundle.getString(key);

        } catch (MissingResourceException ex) {
            return null;
        }
    }
}
