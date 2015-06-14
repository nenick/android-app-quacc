package de.nenick.quacc.view.i18n;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.HashMap;
import java.util.Map;

import de.nenick.quacc.filter.FilterRange;

@EBean
public class FilterRangeTranslator {

    @RootContext
    Context context;

    private static Map<String, FilterRange> translated = new HashMap<>();

    public FilterRange asEnum(String translation) {
        return translated.get(translation);
    }

    public String translate(String type) {
        return translate(FilterRange.valueOf(type));
    }

    public String translate(FilterRange value) {
        String translation = findCachedTranslation(value);
        if (translation == null) {
            translation = translateOrThrow(value);
            translated.put(translation, value);
        }
        return translation;
    }

    private String translateOrThrow(FilterRange value) {
        String translation;
        String resourceName = "filter_range_" + value.name();
        int resourceId = context.getResources().getIdentifier(resourceName, "string", context.getPackageName());
        try {
            translation = context.getString(resourceId);
        } catch (Exception e) {
            throw new IllegalStateException("No translation text found for " + resourceName, e);
        }
        return translation;
    }

    private String findCachedTranslation(FilterRange value) {
        for (Map.Entry<String, FilterRange> entry : translated.entrySet()) {
            if(entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }
}
