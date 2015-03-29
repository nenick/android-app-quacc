package de.nenick.quacc.database.provider;

import com.getbase.android.forger.Forger;

import org.junit.Test;

import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingSelection;
import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.TestDataGraph;
import de.nenick.quacc.database.provider.testdata.base.DataModel;
import de.nenick.quacc.database.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingDbSpec extends RoboDatabaseTest {

    Forger<DataModel> forger = TestDataGraph.access();

    @Test
    public void forger() {
        forger.iNeed(Accounting.class).in(context.getContentResolver());

        AccountingSelection where = new AccountingSelection();
        AccountingCursor accountingCursor = where.query(context.getContentResolver());

        assertThat(accountingCursor.getCount()).isEqualTo(1);
        accountingCursor.moveToFirst();
        assertThat(accountingCursor.getAccountingCategoryId()).isPositive();
    }
/*
    @Test
    public void sampleQuery() {
        AccountingContentValues values = new AccountingContentValues();
        values.putAccountingCategoryId(12);
        values.insert(context.getContentResolver());

        AccountingSelection where = new AccountingSelection().accountingCategoryId(12);
        AccountingCursor accountingCursor = where.query(context.getContentResolver());

        assertThat(accountingCursor.getCount()).isEqualTo(1);
        accountingCursor.moveToFirst();
        assertThat(accountingCursor.getAccountingCategoryId()).isEqualTo(12);
    }

    @Test
    public void sampleUpdate() {
        AccountingContentValues values = new AccountingContentValues();
        values.putAccountingCategoryId(12);
        Uri insert = values.insert(context.getContentResolver());
        long id = Long.parseLong(insert.getLastPathSegment());

        values.putAccountingCategoryId(42);
        values.update(context.getContentResolver(), new AccountingSelection().id(id));

        AccountingSelection where = new AccountingSelection().id(id);
        AccountingCursor accountingCursor = where.query(context.getContentResolver());

        assertThat(accountingCursor.getCount()).isEqualTo(1);
        accountingCursor.moveToFirst();
        assertThat(accountingCursor.getAccountingCategoryId()).isEqualTo(42);
    }
*/
}
