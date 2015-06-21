package de.nenick.quacc.database.accounting;

import android.database.sqlite.SQLiteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;

import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.robolectric.RoboDatabaseTest;
import de.nenick.quacc.testdata.Account;
import de.nenick.quacc.testdata.Category;
import de.nenick.quacc.testdata.TestDataGraph;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingDbTest extends RoboDatabaseTest {

    private final int zero = 0;
    private final String empty = "";
    long accountId;
    String accountingType = "Type";
    String accountingInterval = "Interval";
    long accountingCategoryId;
    String comment = "comment";
    int value = 130;
    long id;
    Date date = new Date();

    AccountingCursor accountingCursor;
    AccountingDb accountingDb;

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Before
    public void setup() {
        accountingDb = AccountingDb_.getInstance_(context);
        accountId = TestDataGraph.access().iNeed(Account.class).in(context.getContentResolver())._id;
        accountingCategoryId = TestDataGraph.access().iNeed(Category.class).in(context.getContentResolver())._id;
    }

    @After
    public void teardown() {
        if(accountingCursor != null) {
            accountingCursor.close();
        }
    }

    @Test
    public void insertShouldAcceptDefaultEntry() {
        whenAccountingIsCreated();
        thenAccountingHasGivenContent();
    }

    @Test
    public void insertShouldRejectZeroValue() {
        expectSQLiteException();
        value = zero;
        whenAccountingIsCreated();
        thenAccountingIsCreatedWithGivenValue();
    }

    @Test
    public void insertShouldRejectNegativeValue() {
        expectSQLiteException();
        value = -1;
        whenAccountingIsCreated();
        thenAccountingIsCreatedWithGivenValue();
    }

    @Test
    public void insertShouldAcceptEmptyComment() {
        comment = empty;
        whenAccountingIsCreated();
        thenAccountingIsCreatedWithGivenComment();
    }

    @Test
    public void insertShouldAcceptNullComment() {
        comment = null;
        whenAccountingIsCreated();
        thenAccountingIsCreatedWithGivenComment();
    }

    @Test
    public void insertShouldRejectNullDate() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("date must not be null");
        date = null;
        whenAccountingIsCreated();
    }

    @Test
    public void insertShouldRejectMissingAccount() {
        expectSQLiteException();
        accountId = zero;
        whenAccountingIsCreated();
    }

    @Test
    public void insertShouldRejectMissingCategory() {
        expectSQLiteException();
        accountingCategoryId = zero;
        whenAccountingIsCreated();
    }

    @Test
    public void insertShouldRejectEmptyType() {
        expectSQLiteException();
        accountingType = empty;
        whenAccountingIsCreated();
    }

    @Test
    public void insertShouldRejectNullType() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("type must not be null");
        accountingType = null;
        whenAccountingIsCreated();
    }

    @Test
    public void insertShouldRejectEmptyInterval() {
        expectSQLiteException();
        accountingInterval = empty;
        whenAccountingIsCreated();
    }

    @Test
    public void insertShouldRejectNullInterval() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("interval must not be null");
        accountingInterval = null;
        whenAccountingIsCreated();
    }

    private void expectSQLiteException() {
        exception.expect(SQLiteException.class);
        exception.expectMessage("Cannot execute for last inserted row ID, base error code: 19");
    }

    private void whenAccountingIsCreated() {
        id = accountingDb.insert(accountId, accountingType, accountingInterval, accountingCategoryId, date, comment, value);
    }

    private void thenAccountingHasGivenContent() {
        accountingCursor = accountingDb.getById(id);
        accountingCursor.moveToFirst();

        assertThat(accountingCursor.getAccountId()).isEqualTo(accountId);
        assertThat(accountingCursor.getCategoryId()).isEqualTo(accountingCategoryId);
        assertThat(accountingCursor.getComment()).isEqualTo(comment);
        assertThat(accountingCursor.getInterval()).isEqualTo(accountingInterval);
        assertThat(accountingCursor.getType()).isEqualTo(accountingType);
        assertThat(accountingCursor.getValue()).isEqualTo(value);
    }

    private void thenAccountingIsCreatedWithGivenValue() {
        accountingCursor = accountingDb.getById(id);
        accountingCursor.moveToFirst();
        assertThat(accountingCursor.getValue()).isEqualTo(value);
    }

    private void thenAccountingIsCreatedWithGivenComment() {
        accountingCursor = accountingDb.getById(id);
        accountingCursor.moveToFirst();

        assertThat(accountingCursor.getComment()).isEqualTo(comment);
    }
}