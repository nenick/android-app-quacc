package de.nenick.quacc.database.interval;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.provider.intervalchange.IntervalChangeCursor;
import de.nenick.quacc.robolectric.RoboDatabaseTest;
import de.nenick.quacc.testdata.Account;
import de.nenick.quacc.testdata.Category;
import de.nenick.quacc.testdata.Interval;
import de.nenick.quacc.testdata.TestDataGraph;

import static org.assertj.core.api.Assertions.assertThat;

public class IntervalChangeDbTest extends RoboDatabaseTest {

    String comment = "comment";
    String change = "change";
    Date date = new Date();
    int value = 42;
    long intervalId;
    long id;

    IntervalChangeCursor intervalChangeCursor;
    IntervalChangeDb intervalChangeDb;

    @Before
    public void setup() {
        intervalChangeDb = IntervalChangeDb_.getInstance_(context);
        Account account = TestDataGraph.access().iNeed(Account.class).in(context.getContentResolver());
        Category category = TestDataGraph.access().iNeed(Category.class).in(context.getContentResolver());
        intervalId = TestDataGraph.access().iNeed(Interval.class).relatedTo(account, category).in(context.getContentResolver())._id;
    }

    @After
    public void teardown() {
        if (intervalChangeCursor != null) {
            intervalChangeCursor.close();
        }
    }

    @Test
    public void insert_shouldAcceptDefaultEntry() {
        whenIntervalChangeIsCreated();
        thenIntervalChangeHasGivenContent();
    }

    @Test
    public void getById_shouldReturnCorrectId() {
        whenIntervalChangeIsCreated();
        whenIntervalChangeIsCreated();
        assertThat(id).isNotEqualTo(intervalId);

        intervalChangeCursor = intervalChangeDb.getById(id);
        intervalChangeCursor.moveToNext();

        assertThat(intervalChangeCursor.getId()).isEqualTo(id);
        assertThat(intervalChangeCursor.getIntervalId()).isEqualTo(intervalId);
    }

    @Test
    public void getAll_shouldReturnCorrectId() {
        whenIntervalChangeIsCreated();
        whenIntervalChangeIsCreated();
        assertThat(id).isNotEqualTo(intervalId);

        intervalChangeCursor = intervalChangeDb.getAll();

        intervalChangeCursor.moveToNext();
        assertThat(intervalChangeCursor.getId()).isNotEqualTo(id);

        intervalChangeCursor.moveToNext();
        assertThat(intervalChangeCursor.getId()).isEqualTo(id);
        assertThat(intervalChangeCursor.getIntervalId()).isEqualTo(intervalId);
    }

    @Test
    public void getAllForInterval_shouldReturnCorrectId() {
        whenIntervalChangeIsCreated();
        whenIntervalChangeIsCreated();
        assertThat(id).isNotEqualTo(intervalId);

        intervalChangeCursor = intervalChangeDb.getAllForInterval(intervalId);

        intervalChangeCursor.moveToNext();
        assertThat(intervalChangeCursor.getId()).isNotEqualTo(id);

        intervalChangeCursor.moveToNext();
        assertThat(intervalChangeCursor.getId()).isEqualTo(id);
        assertThat(intervalChangeCursor.getIntervalId()).isEqualTo(intervalId);
    }

    @Test
    public void getAllForIntervalUntil() {
        date = new Date();
        whenIntervalChangeIsCreated();
        date = new Date(date.getTime() - 300000);
        whenIntervalChangeIsCreated();
        assertThat(id).isNotEqualTo(intervalId);

        intervalChangeCursor = intervalChangeDb.getAllForIntervalUntil(intervalId, date);

        intervalChangeCursor.moveToNext();
        assertThat(intervalChangeCursor.getId()).isEqualTo(id);
        assertThat(intervalChangeCursor.getIntervalId()).isEqualTo(intervalId);
    }

    private void whenIntervalChangeIsCreated() {
        id = intervalChangeDb.insert(intervalId, date, change, comment, value);
    }

    private void thenIntervalChangeHasGivenContent() {
        intervalChangeCursor = intervalChangeDb.getById(id);
        intervalChangeCursor.moveToFirst();

        assertThat(intervalChangeCursor.getIntervalId()).isEqualTo(intervalId);
        assertThat(intervalChangeCursor.getComment()).isEqualTo(comment);
        assertThat(intervalChangeCursor.getChange()).isEqualTo(change);
        assertThat(intervalChangeCursor.getId()).isEqualTo(id);
        assertThat(intervalChangeCursor.getDate()).isEqualTo(date);
    }
}