package de.nenick.quacc.database.interval;

import android.database.sqlite.SQLiteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;

import de.nenick.quacc.database.provider.interval.IntervalCursor;
import de.nenick.quacc.robolectric.RoboDatabaseTest;
import de.nenick.quacc.testdata.Account;
import de.nenick.quacc.testdata.Category;
import de.nenick.quacc.testdata.TestDataGraph;

import static org.assertj.core.api.Assertions.assertThat;

public class IntervalDbTest extends RoboDatabaseTest {

    private final int zero = 0;
    private final String empty = "";
    long accountId;
    String intervalType = "Type";
    String intervalInterval = "Interval";
    long intervalCategoryId;
    String comment = "comment";
    int value = 130;
    long id;
    Date dateStart = new Date();
    Date dateEnd = new Date();
    Date dateLast = new Date();
    Date dateUpdatedUntil = new Date();

    IntervalCursor intervalCursor;
    IntervalDb intervalDb;

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Before
    public void setup() {
        intervalDb = IntervalDb_.getInstance_(context);
        accountId = TestDataGraph.access().iNeed(Account.class).in(context.getContentResolver())._id;
        intervalCategoryId = TestDataGraph.access().iNeed(Category.class).in(context.getContentResolver())._id;
    }

    @After
    public void teardown() {
        if(intervalCursor != null) {
            intervalCursor.close();
        }
    }

    @Test
    public void insert_shouldAcceptDefaultEntry() {
        whenIntervalIsCreated();
        thenIntervalHasGivenContent();
    }

    @Test
    public void insert_shouldRejectZeroValue() {
        expectSQLiteException();
        value = zero;
        whenIntervalIsCreated();
        thenIntervalIsCreatedWithGivenValue();
    }

    @Test
    public void insert_shouldRejectNegativeValue() {
        expectSQLiteException();
        value = -1;
        whenIntervalIsCreated();
        thenIntervalIsCreatedWithGivenValue();
    }

    @Test
    public void insert_shouldAcceptEmptyComment() {
        comment = empty;
        whenIntervalIsCreated();
        thenIntervalIsCreatedWithGivenComment();
    }

    @Test
    public void insert_shouldAcceptNullComment() {
        comment = null;
        whenIntervalIsCreated();
        thenIntervalIsCreatedWithGivenComment();
    }

    @Test
    public void insert_shouldRejectNullDateStart() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("dateStart must not be null");
        dateStart = null;
        whenIntervalIsCreated();
    }

    @Test
    public void insert_shouldRejectNullDateEnd() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("dateEnd must not be null");

        dateEnd = null;
        whenIntervalIsCreated();
    }

    @Test
    public void insert_shouldSetDateLastToNoDateGiven() {
        whenIntervalIsCreated();
        intervalCursor = intervalDb.getById(id);
        intervalCursor.moveToNext();
        assertThat(intervalCursor.getDateLast()).isEqualTo(IntervalDb.NO_DATE_GIVEN);
    }

    @Test
    public void insert_shouldSetDateUpdateUntilToNoDateGiven() {
        whenIntervalIsCreated();
        intervalCursor = intervalDb.getById(id);
        intervalCursor.moveToNext();
        assertThat(intervalCursor.getDateUpdatedUntil()).isEqualTo(IntervalDb.NO_DATE_GIVEN);
    }

    @Test
    public void insert_shouldRejectMissingAccount() {
        expectSQLiteException();
        accountId = zero;
        whenIntervalIsCreated();
    }

    @Test
    public void insert_shouldRejectMissingCategory() {
        expectSQLiteException();
        intervalCategoryId = zero;
        whenIntervalIsCreated();
    }

    @Test
    public void insert_shouldRejectEmptyType() {
        expectSQLiteException();
        intervalType = empty;
        whenIntervalIsCreated();
    }

    @Test
    public void insert_shouldRejectNullType() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("type must not be null");
        intervalType = null;
        whenIntervalIsCreated();
    }

    @Test
    public void insert_shouldRejectEmptyInterval() {
        expectSQLiteException();
        intervalInterval = empty;
        whenIntervalIsCreated();
    }

    @Test
    public void insert_shouldRejectNullInterval() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("interval must not be null");
        intervalInterval = null;
        whenIntervalIsCreated();
    }

    @Test
    public void getAll_shouldReturnCorrectId() {
        whenIntervalIsCreated();
        whenIntervalIsCreated();
        assertThat(id).isNotEqualTo(accountId);
        assertThat(id).isNotEqualTo(intervalCategoryId);

        intervalCursor = intervalDb.getAll();

        intervalCursor.moveToNext();
        assertThat(intervalCursor.getId()).isNotEqualTo(id);

        intervalCursor.moveToNext();
        assertThat(intervalCursor.getId()).isEqualTo(id);
        assertThat(intervalCursor.getAccountId()).isEqualTo(accountId);
        assertThat(intervalCursor.getCategoryId()).isEqualTo(intervalCategoryId);
    }

    @Test
    public void getById_shouldReturnCorrectId() {
        whenIntervalIsCreated();
        whenIntervalIsCreated();
        assertThat(id).isNotEqualTo(accountId);
        assertThat(id).isNotEqualTo(intervalCategoryId);

        intervalCursor = intervalDb.getById(id);
        intervalCursor.moveToNext();

        assertThat(intervalCursor.getId()).isEqualTo(id);
        assertThat(intervalCursor.getAccountId()).isEqualTo(accountId);
        assertThat(intervalCursor.getCategoryId()).isEqualTo(intervalCategoryId);
    }

    @Test
    public void getAllBetween_shouldReturnCorrectId() {
        dateStart = new Date(new Date().getTime() - 300000);
        whenIntervalIsCreated();
        dateStart = new Date();
        whenIntervalIsCreated();
        assertThat(id).isNotEqualTo(accountId);
        assertThat(id).isNotEqualTo(intervalCategoryId);

        intervalCursor = intervalDb.getAllBetween(accountId, new Date(new Date().getTime() - 100000), new Date());
        assertThat(intervalCursor.getCount()).isEqualTo(1);
        intervalCursor.moveToNext();

        assertThat(intervalCursor.getId()).isEqualTo(id);
        assertThat(intervalCursor.getAccountId()).isEqualTo(accountId);
        assertThat(intervalCursor.getCategoryId()).isEqualTo(intervalCategoryId);
    }

    @Test
    public void deleteById() {
        whenIntervalIsCreated();
        intervalDb.deleteById(id);
        intervalCursor = intervalDb.getAll();
        assertThat(intervalCursor.getCount()).isZero();
    }

    @Test
    public void updatedUntil() {
        whenIntervalIsCreated();
        intervalDb.updatedUntil(id, dateLast, dateUpdatedUntil);
        intervalCursor = intervalDb.getById(id);
        intervalCursor.moveToNext();
        assertThat(intervalCursor.getDateLast()).isEqualTo(dateLast);
        assertThat(intervalCursor.getDateUpdatedUntil()).isEqualTo(dateUpdatedUntil);
    }

    @Test
    public void getAllForAccountNotUpdatedUntil() {
        whenIntervalIsCreated();
        whenIntervalIsCreated();
        intervalDb.updatedUntil(id, dateLast, dateUpdatedUntil);
        intervalCursor = intervalDb.getAllForAccountNotUpdatedUntil(accountId, dateUpdatedUntil);
        assertThat(intervalCursor.getCount()).isEqualTo(1);
    }

    @Test
    public void deleteAll() {
        whenIntervalIsCreated();
        whenIntervalIsCreated();
        intervalDb.deleteAll();
        intervalCursor = intervalDb.getAll();
        assertThat(intervalCursor.getCount()).isZero();
    }

    private void expectSQLiteException() {
        exception.expect(SQLiteException.class);
        exception.expectMessage("Cannot execute for last inserted row ID, base error code: 19");
    }

    private void whenIntervalIsCreated() {
        id = intervalDb.insert(accountId, intervalType, intervalInterval, intervalCategoryId, dateStart, dateEnd, comment, value);
    }

    private void thenIntervalHasGivenContent() {
        intervalCursor = intervalDb.getById(id);
        intervalCursor.moveToFirst();

        assertThat(intervalCursor.getAccountId()).isEqualTo(accountId);
        assertThat(intervalCursor.getCategoryId()).isEqualTo(intervalCategoryId);
        assertThat(intervalCursor.getComment()).isEqualTo(comment);
        assertThat(intervalCursor.getInterval()).isEqualTo(intervalInterval);
        assertThat(intervalCursor.getType()).isEqualTo(intervalType);
        assertThat(intervalCursor.getValue()).isEqualTo(value);
    }

    private void thenIntervalIsCreatedWithGivenValue() {
        intervalCursor = intervalDb.getById(id);
        intervalCursor.moveToFirst();
        assertThat(intervalCursor.getValue()).isEqualTo(value);
    }

    private void thenIntervalIsCreatedWithGivenComment() {
        intervalCursor = intervalDb.getById(id);
        intervalCursor.moveToFirst();

        assertThat(intervalCursor.getComment()).isEqualTo(comment);
    }
}