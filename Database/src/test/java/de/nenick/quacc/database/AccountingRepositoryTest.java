package de.nenick.quacc.database;

import com.getbase.android.forger.Forger;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingType;
import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.TestDataGraph;
import de.nenick.quacc.database.provider.testdata.base.DataModel;
import de.nenick.quacc.database.robolectric.RoboDatabaseTest;
import de.nenick.quacc.database.tools.TestDatabaseDateUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingRepositoryTest extends RoboDatabaseTest {

    Forger<DataModel> forger = TestDataGraph.access();
    AccountingRepository repository;

    Date date = TestDatabaseDateUtil.parse("21.12.2000");

    @Before
    public void setUp() {
        repository = AccountingRepository_.getInstance_(context);
    }

    @Test
    public void shouldReturnAccountings() {
        forger.iNeed(Accounting.class).in(context.getContentResolver());

        AccountingCursor accountings = repository.getAccountings();
        assertThat(accountings.getCount()).isPositive();
    }

    @Test
    public void shouldInsertAccounting() throws ParseException {
        assertThat(repository.getAccountings().getCount()).isEqualTo(0);

        repository.insertAccounting(1, AccountingType.Ausgabe, AccountingInterval.Alle_3_Monate, 2, date, 4200, "my comment");

        AccountingCursor accountings = repository.getAccountings();
        assertThat(accountings.getCount()).isEqualTo(1);
        accountings.moveToNext();
        assertThat(accountings.getAccountId()).isEqualTo(1);
        assertThat(accountings.getAccountingType()).isEqualTo(AccountingType.Ausgabe);
        assertThat(accountings.getAccountingInterval()).isEqualTo(AccountingInterval.Alle_3_Monate);
        assertThat(accountings.getAccountingCategoryId()).isEqualTo(2);
        assertThat(accountings.getAccountingDate()).isEqualTo(date);
        assertThat(accountings.getValue()).isEqualTo(4200);
        assertThat(accountings.getComment()).isEqualTo("my comment");
    }
}