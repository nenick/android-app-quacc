package de.nenick.quacc.database.account;

import android.database.Cursor;
import android.net.Uri;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.BaseRepository;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;

@EBean
public class AccountRepository extends BaseRepository<AccountContentValues, AccountSpec, AccountCursor> {

    @Override
    public void update(AccountContentValues values, AccountSpec specification) {
        AccountSelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public AccountCursor query(AccountSpec specification) {
        AccountSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver());
    }

    @Override
    public Uri uri() {
        return AccountColumns.CONTENT_URI;
    }
}
