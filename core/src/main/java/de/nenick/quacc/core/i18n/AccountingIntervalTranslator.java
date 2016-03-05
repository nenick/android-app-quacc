package de.nenick.quacc.core.i18n;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.HashMap;
import java.util.Map;

import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;

@EBean
public class AccountingIntervalTranslator {

    @RootContext
    Context context;

    private static Map<String, BookingIntervalOption> translated = new HashMap<>();

    public BookingIntervalOption asEnum(String translation) {
        return translated.get(translation);
    }

    public String translate(String interval) {
        return translate(BookingIntervalOption.valueOf(interval));
    }

    public String translate(BookingIntervalOption interval) {
        String translation = findCachedTranslation(interval);
        if (translation == null) {
            translation = translateOrThrow(interval);
            translated.put(translation, interval);
        }
        return translation;
    }

    private String translateOrThrow(BookingIntervalOption interval) {
        String translation;
        String resourceName = "accounting_interval_" + interval.name();
        int resourceId = context.getResources().getIdentifier(resourceName, "string", context.getPackageName());
        try {
            translation = context.getString(resourceId);
        } catch (Exception e) {
            throw new IllegalStateException("No translation text found for " + resourceName, e);
        }
        return translation;
    }

    private String findCachedTranslation(BookingIntervalOption interval) {
        for (Map.Entry<String, BookingIntervalOption> entry : translated.entrySet()) {
            if(entry.getValue() == interval) {
                return entry.getKey();
            }
        }
        return null;
    }
}
