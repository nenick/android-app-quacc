package de.nenick.quacc.database.template;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateColumns;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateContentValues;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.interval.IntervalColumns;

@EBean
public class AccountingTemplateDb {

    private final String[] ALL_COLUMN_AND_FROM_JOIN = ObjectArrays.concat(ObjectArrays.concat(AccountingTemplateColumns.ALL_COLUMNS, AccountColumns.ALL_COLUMNS, String.class), CategoryColumns.ALL_COLUMNS, String.class);

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
        return new AccountingTemplateSelection().id(id).query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }

    public AccountingTemplateCursor getAll() {
        return new AccountingTemplateSelection().query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }
}
