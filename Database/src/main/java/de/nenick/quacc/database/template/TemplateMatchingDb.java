package de.nenick.quacc.database.template;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingColumns;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingContentValues;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingCursor;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingSelection;

@EBean
public class TemplateMatchingDb {

    private final String[] ALL_COLUMN_AND_FROM_JOIN = ObjectArrays.concat(TemplateMatchingColumns.ALL_COLUMNS, AccountingTemplateColumns.ALL_COLUMNS, String.class);

    @RootContext
    Context context;

    public long insert(String text, long templateId) {
        Uri uri = new TemplateMatchingContentValues().putAccountingTemplateId(templateId).putText(text).insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public TemplateMatchingCursor getById(long id) {
        return new TemplateMatchingSelection().id(id).query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }

    public TemplateMatchingCursor getAll() {
        return new TemplateMatchingSelection().query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }

    public String getSpeechTextForTemplate(long templateId) {
        TemplateMatchingCursor query = new TemplateMatchingSelection().accountingTemplateId(templateId).query(context.getContentResolver());
        String result = "";
        if(query.moveToFirst()) {
            result = query.getText();
        }
        query.close();
        return result;
    }

    public void deleteAll() {
        new TemplateMatchingSelection().delete(context.getContentResolver());
    }
}
