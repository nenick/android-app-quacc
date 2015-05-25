package de.nenick.quacc.database;

import com.getbase.android.forger.Forger;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.TestDataGraph;
import de.nenick.quacc.database.provider.testdata.base.DataModel;
import de.nenick.quacc.database.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingRepositoryTest extends RoboDatabaseTest {

    Forger<DataModel> forger = TestDataGraph.access();


    Date date = new Date(System.currentTimeMillis());


    @Test
    public void shouldReturnAccountings() {
        forger.iNeed(Accounting.class).in(context.getContentResolver());

        AccountingCursor accountings = AccountingDb_.getInstance_(context).getAll();
        assertThat(accountings.getCount()).isPositive();
    }
}