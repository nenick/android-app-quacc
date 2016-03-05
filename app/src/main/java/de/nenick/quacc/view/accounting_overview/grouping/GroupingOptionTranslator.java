package de.nenick.quacc.view.accounting_overview.grouping;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.HashMap;
import java.util.Map;

import de.nenick.quacc.view.accounting_overview.grouping.GroupingOption;

@EBean
public class GroupingOptionTranslator {

    @RootContext
    Context context;

    private static Map<String, GroupingOption> translated = new HashMap<>();

    public GroupingOption asEnum(String translation) {
        return translated.get(translation);
    }

    public String translate(String type) {
        return translate(GroupingOption.valueOf(type));
    }

    public String translate(GroupingOption value) {
        String translation = findCachedTranslation(value);
        if (translation == null) {
            translation = translateOrThrow(value);
            translated.put(translation, value);
        }
        return translation;
    }

    private String translateOrThrow(GroupingOption value) {
        String translation;
        String resourceName = "grouping_option_" + value.name();
        int resourceId = context.getResources().getIdentifier(resourceName, "string", context.getPackageName());
        try {
            translation = context.getString(resourceId);
        } catch (Exception e) {
            throw new IllegalStateException("No translation text found for " + resourceName, e);
        }
        return translation;
    }

    private String findCachedTranslation(GroupingOption value) {
        for (Map.Entry<String, GroupingOption> entry : translated.entrySet()) {
            if(entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }
}
