package de.nenick.quacc.database;

import com.getbase.android.forger.Forger;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.TestDataGraph;
import de.nenick.quacc.database.provider.testdata.base.DataModel;
import de.nenick.quacc.database.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingRepositoryTest extends RoboDatabaseTest {

    Forger<DataModel> forger = TestDataGraph.access();
    AccountingRepository repository;

    @Before
    public void setUp() {
        repository = AccountingRepository_.getInstance_(context);
    }

    @Test
    public void shouldReturnAccounts() {
        forger.iNeed(Accounting.class).in(context.getContentResolver());

        AccountingCursor accountings = repository.getAccountings();
        assertThat(accountings.getCount()).isPositive();
    }
}