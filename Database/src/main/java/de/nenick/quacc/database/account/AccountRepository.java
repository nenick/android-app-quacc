package de.nenick.quacc.database.account;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.base.Repository;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;

@EBean
public class AccountRepository extends Repository<AccountContentValues, AccountSpec, AccountSelection, AccountCursor> {

    @Override
    public void update(AccountContentValues values, AccountSpec specification) {
        AccountSelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public AccountCursor query(QuerySpecification<AccountSelection> specification) {
        AccountSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver());
    }
}
