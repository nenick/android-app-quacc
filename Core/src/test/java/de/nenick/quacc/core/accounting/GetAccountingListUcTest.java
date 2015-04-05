package de.nenick.quacc.core.accounting;

import com.getbase.android.forger.Forger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.robolectric.RoboCoreTest;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.TestDataGraph;
import de.nenick.quacc.database.provider.testdata.base.DataModel;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAccountingListUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountingListUc getAccountingListUc;

    Forger<DataModel> forger = TestDataGraph.access();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDeliverAccountingCursor() {
        forger.iNeed(3).of(Accounting.class)
                .with(AccountingColumns.ACCOUNTING_INTERVAL, 1)
                .with(AccountingColumns.ACCOUNTING_TYPE, 1)
                .in(context.getContentResolver());

        AccountingCursor apply = getAccountingListUc.apply();
        assertThat(apply.getCount()).isPositive();
    }
}