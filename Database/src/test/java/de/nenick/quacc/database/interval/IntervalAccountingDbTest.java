package de.nenick.quacc.database.interval;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.database.provider.intervalaccounting.IntervalAccountingCursor;
import de.nenick.quacc.robolectric.RoboDatabaseTest;
import de.nenick.quacc.testdata.Account;
import de.nenick.quacc.testdata.Accounting;
import de.nenick.quacc.testdata.Category;
import de.nenick.quacc.testdata.Interval;
import de.nenick.quacc.testdata.TestDataGraph;

import static org.assertj.core.api.Assertions.assertThat;

public class IntervalAccountingDbTest extends RoboDatabaseTest {

    IntervalAccountingCursor intervalAccountingCursor;
    IntervalAccountingDb intervalAccountingDb;
    long id;
    long intervalId;
    long accountingId;

    @Before
    public void setup() {
        intervalAccountingDb = IntervalAccountingDb_.getInstance_(context);
        Account account = TestDataGraph.access().iNeed(Account.class).in(context.getContentResolver());
        Category category = TestDataGraph.access().iNeed(Category.class).in(context.getContentResolver());
        intervalId = TestDataGraph.access().iNeed(Interval.class).relatedTo(account, category).in(context.getContentResolver())._id;
        accountingId = TestDataGraph.access().iNeed(Accounting.class).relatedTo(account, category).in(context.getContentResolver())._id;
    }

    @After
    public void teardown() {
        if (intervalAccountingCursor != null) {
            intervalAccountingCursor.close();
        }
    }

    @Test
    public void insert_shouldAcceptDefaultEntry() {
        whenIntervalAccountingIsCreated();
        thenIntervalAccountingHasGivenContent();
    }

    @Test
    public void getAll() {
        whenIntervalAccountingIsCreated();
        intervalAccountingCursor = intervalAccountingDb.getAll();
        assertThat(intervalAccountingCursor.getCount()).isEqualTo(1);
    }

    private void whenIntervalAccountingIsCreated() {
        id = intervalAccountingDb.insert(intervalId, accountingId);
    }

    private void thenIntervalAccountingHasGivenContent() {
        intervalAccountingCursor = intervalAccountingDb.getById(id);
        intervalAccountingCursor.moveToFirst();

        assertThat(intervalAccountingCursor.getId()).isEqualTo(id);
        assertThat(intervalAccountingCursor.getIntervalId()).isEqualTo(intervalId);
        assertThat(intervalAccountingCursor.getAccountingId()).isEqualTo(accountingId);
    }
}