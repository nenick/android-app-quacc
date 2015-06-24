package de.nenick.quacc.i18n;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.HashMap;
import java.util.Map;

import de.nenick.quacc.core.accounting.type.AccountingType;

@EBean
public class AccountingTypeTranslator {

    @RootContext
    Context context;

    private static Map<String, AccountingType> translated = new HashMap<>();

    public AccountingType asEnum(String translation) {
        return translated.get(translation);
    }

    public String translate(String type) {
        return translate(AccountingType.valueOf(type));
    }

    public String translate(AccountingType type) {
        String translation = findCachedTranslation(type);
        if (translation == null) {
            translation = translateOrThrow(type);
            translated.put(translation, type);
        }
        return translation;
    }

    private String translateOrThrow(AccountingType interval) {
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

    private String findCachedTranslation(AccountingType interval) {
        for (Map.Entry<String, AccountingType> entry : translated.entrySet()) {
            if(entry.getValue() == interval) {
                return entry.getKey();
            }
        }
        return null;
    }
}
