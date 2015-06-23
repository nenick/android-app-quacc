package de.nenick.quacc.database.template;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateContentValues;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateSelection;

@EBean
public class AccountingTemplateDb {

    @RootContext
    Context context;

    public long insert(long accountId, String accountingType, String accountingInterval, long accountingCategoryId, String comment, int value) {
        Uri uri = new AccountingTemplateContentValues()
                .putAccountId(accountId)
                .putType(accountingType)
                .putInterval(accountingInterval)
                .putCategoryId(accountingCategoryId)
                .putComment(comment)
                .putValue(value).insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public AccountingTemplateCursor getById(long id) {
        return new AccountingTemplateSelection().id(id).query(context.getContentResolver());
    }

    public AccountingTemplateCursor getAll() {
        return new AccountingTemplateSelection().query(context.getContentResolver());
    }
}
