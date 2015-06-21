package de.nenick.quacc.database.template;


import android.database.sqlite.SQLiteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.nenick.quacc.database.provider.templatematching.TemplateMatchingCursor;
import de.nenick.quacc.robolectric.RoboDatabaseTest;
import de.nenick.quacc.testdata.Account;
import de.nenick.quacc.testdata.Accounting_Template;
import de.nenick.quacc.testdata.Category;
import de.nenick.quacc.testdata.TestDataGraph;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateMatchingDbTest extends RoboDatabaseTest {

    TemplateMatchingCursor templateMatchingCursor;
    TemplateMatchingDb templateMatchingDb;

    long templateId;
    String text = "text";
    long id;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        templateMatchingDb = TemplateMatchingDb_.getInstance_(context);
        Account account = TestDataGraph.access().iNeed(Account.class).in(context.getContentResolver());
        Category category = TestDataGraph.access().iNeed(Category.class).in(context.getContentResolver());
        templateId = TestDataGraph.access().iNeed(Accounting_Template.class).relatedTo(account, category).in(context.getContentResolver())._id;
    }

    @After
    public void teardown() {
        if (templateMatchingCursor != null) {
            templateMatchingCursor.close();
        }
    }

    @Test
    public void insertShouldAcceptDefaultEntry() {
        whenTemplateMatchingIsCreated();
        thenTemplateMatchingHasGivenContent();
    }

    @Test
    public void insertShouldRejectEmptyText() {
        expectSQLiteException();
        text = "";
        whenTemplateMatchingIsCreated();
    }

    @Test
    public void insertShouldRejectNullText() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("text must not be null");
        text = null;
        whenTemplateMatchingIsCreated();
    }

    @Test
    public void insertShouldRejectMissingAccountingTemplate() {
        expectSQLiteException();
        templateId = 0;
        whenTemplateMatchingIsCreated();
    }

    private void expectSQLiteException() {
        exception.expect(SQLiteException.class);
        exception.expectMessage("Cannot execute for last inserted row ID, base error code: 19");
    }

    private void thenTemplateMatchingHasGivenContent() {
        templateMatchingCursor = templateMatchingDb.getById(id);
        templateMatchingCursor.moveToFirst();
        assertThat(templateMatchingCursor.getId()).isEqualTo(id);
        assertThat(templateMatchingCursor.getAccountingTemplateId()).isEqualTo(templateId);
        assertThat(templateMatchingCursor.getText()).isEqualTo(text);
    }

    private void whenTemplateMatchingIsCreated() {
        id = templateMatchingDb.insert(text, templateId);
    }
}