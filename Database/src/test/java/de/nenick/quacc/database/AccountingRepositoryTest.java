package de.nenick.quacc.database;

import com.getbase.android.forger.Forger;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.TestDataGraph;
import de.nenick.quacc.database.provider.testdata.base.DataModel;
import de.nenick.quacc.database.robolectric.RoboDatabaseTest;
import de.nenick.quacc.database.tools.TestDatabaseDateUtil;

public class AccountingRepositoryTest extends RoboDatabaseTest {

    Forger<DataModel> forger = TestDataGraph.access();


    Date date = TestDatabaseDateUtil.parse("21.12.2000");


    @Test
    public void shouldReturnAccountings() {
        forger.iNeed(Accounting.class).in(context.getContentResolver());

        //AccountingCursor accountings = repository.getAccountings();
        //assertThat(accountings.getCount()).isPositive();
    }
}