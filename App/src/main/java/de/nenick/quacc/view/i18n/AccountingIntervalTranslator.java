package de.nenick.quacc.view.i18n;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.HashMap;
import java.util.Map;

import de.nenick.quacc.accounting.interval.AccountingInterval;

@EBean
public class AccountingIntervalTranslator {

    @RootContext
    Context context;

    private static Map<String, AccountingInterval> translated = new HashMap<>();

    public AccountingInterval asEnum(String translation) {
        return translated.get(translation);
    }

    public String translate(String interval) {
        return translate(AccountingInterval.valueOf(interval));
    }

    public String translate(AccountingInterval interval) {
        String translation = findCachedTranslation(interval);
        if (translation == null) {
            translation = translateOrThrow(interval);
            translated.put(translation, interval);
        }
        return translation;
    }

    private String translateOrThrow(AccountingInterval interval) {
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

    private String findCachedTranslation(AccountingInterval interval) {
        for (Map.Entry<String, AccountingInterval> entry : translated.entrySet()) {
            if(entry.getValue() == interval) {
                return entry.getKey();
            }
        }
        return null;
    }
}
