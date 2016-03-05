package de.nenick.quacc.core.i18n;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.HashMap;
import java.util.Map;

import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;

@EBean
public class AccountingTypeTranslator {

    @RootContext
    Context context;

    private static Map<String, BookingDirectionOption> translated = new HashMap<>();

    public BookingDirectionOption asEnum(String translation) {
        return translated.get(translation);
    }

    public String translate(String type) {
        return translate(BookingDirectionOption.valueOf(type));
    }

    public String translate(BookingDirectionOption type) {
        String translation = findCachedTranslation(type);
        if (translation == null) {
            translation = translateOrThrow(type);
            translated.put(translation, type);
        }
        return translation;
    }

    private String translateOrThrow(BookingDirectionOption interval) {
        String translation;
        String resourceName = "accounting_type_" + interval.name();
        int resourceId = context.getResources().getIdentifier(resourceName, "string", context.getPackageName());
        try {
            translation = context.getString(resourceId);
        } catch (Exception e) {
            throw new IllegalStateException("No translation text found for " + resourceName, e);
        }
        return translation;
    }

    private String findCachedTranslation(BookingDirectionOption interval) {
        for (Map.Entry<String, BookingDirectionOption> entry : translated.entrySet()) {
            if(entry.getValue() == interval) {
                return entry.getKey();
            }
        }
        return null;
    }
}
