package de.nenick.quacc.database.provider;

import com.getbase.android.forger.Forger;

import org.chalup.microorm.MicroOrm;
import org.chalup.thneed.ModelGraph;
import org.junit.Test;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingSelection;
import de.nenick.quacc.database.provider.testdata.Account;
import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.Accounting_Category;
import de.nenick.quacc.database.provider.testdata.Accounting_Interval;
import de.nenick.quacc.database.provider.testdata.base.BaseTestModel;
import de.nenick.quacc.database.provider.testdata.base.DataModel;
import de.nenick.quacc.database.robolectric.RobolectricSupportedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingDbSpec extends RobolectricSupportedTest {

    public static BaseTestModel ACCOUNT = new BaseTestModel(Account.class);
    public static BaseTestModel ACCOUNTING = new BaseTestModel(Accounting.class);
    public static BaseTestModel ACCOUNTING_INTERVAL = new BaseTestModel(Accounting_Interval.class);
    public static BaseTestModel ACCOUNTING_CATEGORY = new BaseTestModel(Accounting_Category.class);

    static ModelGraph<DataModel> MODEL_GRAPH = ModelGraph.of(DataModel.class)
            .identifiedByDefault().by("_id")
            .where()
            .the(ACCOUNTING).references(ACCOUNT).by(AccountingColumns.ACCOUNT_ID)
            .the(ACCOUNTING).references(ACCOUNTING_CATEGORY).by(AccountingColumns.ACCOUNTING_CATEGORY_ID)
            .the(ACCOUNTING).references(ACCOUNTING_INTERVAL).by(AccountingColumns.ACCOUNTING_INTERVAL_ID)
            .build();

    Forger<DataModel> forger = new Forger<>(MODEL_GRAPH, new MicroOrm());

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
