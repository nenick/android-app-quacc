package de.nenick.quacc.database.template;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.templatematching.TemplateMatchingContentValues;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingCursor;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingSelection;

@EBean
public class TemplateMatchingDb {

    @RootContext
    Context context;

    public long insert(String text, long templateId) {
        Uri uri = new TemplateMatchingContentValues().putAccountingTemplateId(templateId).putText(text).insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public TemplateMatchingCursor getById(long id) {
        return new TemplateMatchingSelection().id(id).query(context.getContentResolver());
    }
}
