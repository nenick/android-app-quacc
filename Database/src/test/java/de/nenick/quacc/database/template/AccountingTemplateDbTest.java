package de.nenick.quacc.database.template;

import android.database.sqlite.SQLiteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.robolectric.RoboDatabaseTest;
import de.nenick.quacc.testdata.Account;
import de.nenick.quacc.testdata.Category;
import de.nenick.quacc.testdata.TestDataGraph;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingTemplateDbTest extends RoboDatabaseTest {

    final int zero = 0;
    final String empty = "";

    long accountId;
    String accountingType = "Type";
    String accountingInterval = "Interval";
    long accountingCategoryId;
    String comment = "comment";
    int value = 130;
    long id;

    AccountingTemplateCursor accountingTemplateCursor;
    AccountingTemplateDb accountingTemplateDb;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        accountingTemplateDb = AccountingTemplateDb_.getInstance_(context);
        accountId = TestDataGraph.access().iNeed(Account.class).in(context.getContentResolver())._id;
        accountingCategoryId = TestDataGraph.access().iNeed(Category.class).in(context.getContentResolver())._id;
    }

    @After
    public void teardown() {
        if (accountingTemplateCursor != null) {
            accountingTemplateCursor.close();
        }
    }

    @Test
    public void insertShouldAcceptDefaultEntry() {
        whenAccountingTemplateIsCreated();
        thenAccountingTemplateHasGivenContent();
    }

    @Test
    public void insertShouldAcceptZeroValue() {
        value = zero;
        whenAccountingTemplateIsCreated();
        thenAccountingIsCreatedWithGivenValue();
    }

    @Test
    public void insertShouldAcceptEmptyComment() {
        comment = empty;
        whenAccountingTemplateIsCreated();
        thenAccountingTemplateIsCreatedWithGivenComment();
    }

    @Test
    public void insertShouldAcceptNullComment() {
        comment = empty;
        whenAccountingTemplateIsCreated();
        thenAccountingTemplateIsCreatedWithGivenComment();
    }

    @Test
    public void insertShouldRejectMissingAccount() {
        expectSQLiteException();
        accountId = zero;
        whenAccountingTemplateIsCreated();
    }

    @Test
    public void insertShouldRejectMissingCategory() {
        expectSQLiteException();
        accountingCategoryId = zero;
        whenAccountingTemplateIsCreated();
    }

    @Test
    public void insertShouldRejectEmptyType() {
        expectSQLiteException();
        accountingType = empty;
        whenAccountingTemplateIsCreated();
    }

    @Test
    public void insertShouldRejectNullType() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("type must not be null");
        accountingType = null;
        whenAccountingTemplateIsCreated();
    }

    @Test
    public void insertShouldRejectEmptyInterval() {
        expectSQLiteException();
        accountingInterval = empty;
        whenAccountingTemplateIsCreated();
    }

    @Test
    public void insertShouldRejectNullInterval() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("interval must not be null");
        accountingInterval = null;
        whenAccountingTemplateIsCreated();
    }

    @Test
    public void getById_shouldReturnCorrectId() {
        whenAccountingTemplateIsCreated();
        whenAccountingTemplateIsCreated();
        assertThat(id).isNotEqualTo(accountId);
        assertThat(id).isNotEqualTo(accountingCategoryId);

        accountingTemplateCursor = accountingTemplateDb.getById(id);
        accountingTemplateCursor.moveToNext();

        assertThat(accountingTemplateCursor.getId()).isEqualTo(id);
        assertThat(accountingTemplateCursor.getAccountId()).isEqualTo(accountId);
        assertThat(accountingTemplateCursor.getCategoryId()).isEqualTo(accountingCategoryId);
    }

    @Test
    public void getAll_shouldReturnCorrectId() {
        whenAccountingTemplateIsCreated();
        whenAccountingTemplateIsCreated();
        assertThat(id).isNotEqualTo(accountId);
        assertThat(id).isNotEqualTo(accountingCategoryId);

        accountingTemplateCursor = accountingTemplateDb.getAll();
        accountingTemplateCursor.moveToNext();
        accountingTemplateCursor.moveToNext();

        assertThat(accountingTemplateCursor.getId()).isEqualTo(id);
        assertThat(accountingTemplateCursor.getAccountId()).isEqualTo(accountId);
        assertThat(accountingTemplateCursor.getCategoryId()).isEqualTo(accountingCategoryId);

    }

    private void expectSQLiteException() {
        exception.expect(SQLiteException.class);
        exception.expectMessage("Cannot execute for last inserted row ID, base error code: 19");
    }

    private void whenAccountingTemplateIsCreated() {
        id = accountingTemplateDb.insert(accountId, accountingType, accountingInterval, accountingCategoryId, comment, value);
    }

    private void thenAccountingTemplateHasGivenContent() {
        accountingTemplateCursor = accountingTemplateDb.getById(id);
        accountingTemplateCursor.moveToFirst();

        assertThat(accountingTemplateCursor.getAccountId()).isEqualTo(accountId);
        assertThat(accountingTemplateCursor.getCategoryId()).isEqualTo(accountingCategoryId);
        assertThat(accountingTemplateCursor.getComment()).isEqualTo(comment);
        assertThat(accountingTemplateCursor.getInterval()).isEqualTo(accountingInterval);
        assertThat(accountingTemplateCursor.getType()).isEqualTo(accountingType);
        assertThat(accountingTemplateCursor.getValue()).isEqualTo(value);
    }

    private void thenAccountingIsCreatedWithGivenValue() {
        accountingTemplateCursor = accountingTemplateDb.getById(id);
        accountingTemplateCursor.moveToFirst();
        assertThat(accountingTemplateCursor.getValue()).isEqualTo(value);
    }

    private void thenAccountingTemplateIsCreatedWithGivenComment() {
        accountingTemplateCursor = accountingTemplateDb.getById(id);
        accountingTemplateCursor.moveToFirst();

        assertThat(accountingTemplateCursor.getComment()).isEqualTo(comment);
    }
}