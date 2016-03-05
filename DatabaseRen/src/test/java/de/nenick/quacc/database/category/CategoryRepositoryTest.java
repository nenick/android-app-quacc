package de.nenick.quacc.database.category;


import android.database.sqlite.SQLiteException;

import org.junit.Test;

import de.nenick.quacc.database.provider.category.CategoryContentValues;

import static de.nenick.quacc.database.testsupport.CauseMatcher.containsMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryRepositoryTest extends CategoryTestBase {

    @Test
    public void testInsertMinimalContent() {
        givenMandatoryContent();
        whenInsertContent();
        thenEntryIsInserted();
    }

    @Test
    public void testValue_Name_NotEmpty() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putName("");
        whenInsertContent();
    }

    @Test
    public void testValue_Name_NotNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("name must not be null");

        givenMandatoryContent();
        //noinspection ConstantConditions
        values.putName(null);
        whenInsertContent();
    }

    @Test
    public void testValue_Name_AllowDuplicate() {
        givenMandatoryContent();
        values.putName("Same name");
        whenInsertContent();

        givenMandatoryContent();
        values.putName("Same name");
        thenEntryIsInserted();
    }

    @Test
    public void testValue_Section_NotEmpty() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putSection("");
        whenInsertContent();
    }

    @Test
    public void testValue_Section_NotNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("section must not be null");

        givenMandatoryContent();
        //noinspection ConstantConditions
        values.putSection(null);
        whenInsertContent();
    }

    @Test
    public void testValue_Section_AllowDuplicate() {
        givenMandatoryContent();
        values.putSection("Same section");
        whenInsertContent();

        givenMandatoryContent();
        values.putSection("Same section");
        thenEntryIsInserted();
    }

    @Test
    public void testValue_SectionAndName_NotDuplicate() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("columns category__name, category__section are not unique"));

        givenMandatoryContent();
        values.putSection("Same section");
        values.putName("Same name");
        whenInsertContent();

        givenMandatoryContent();
        values.putSection("Same section");
        values.putName("Same name");
        whenInsertContent();
    }

    @Test
    public void testValue_Interval_NotEmpty() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putInterval("");
        whenInsertContent();
    }

    @Test
    public void testValue_Interval_NotNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("interval must not be null");

        givenMandatoryContent();
        //noinspection ConstantConditions
        values.putInterval(null);
        whenInsertContent();
    }

    @Test
    public void testValue_Direction_NotEmpty() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putDirection("");
        whenInsertContent();
    }

    @Test
    public void testValue_Direction_NotNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("direction must not be null");

        givenMandatoryContent();
        //noinspection ConstantConditions
        values.putDirection(null);
        whenInsertContent();
    }

    @Test
    public void testValue_Level_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("level may not be NULL"));

        givenMandatoryContentWithoutLevel();
        whenInsertContent();
    }

    @Test
    public void testValue_Level_AllowZeroAndOne() {
        givenMandatoryContentWithoutLevel();
        values.putLevel(0);
        whenInsertContent();
        thenEntryIsInserted();

        values.putName("sub");
        values.putLevel(1);
        whenInsertContent();
        thenEntryIsInserted();
    }

    @Test
    public void testValue_Level_NotGreaterThanOne() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContentWithoutLevel();
        values.putLevel(2);
        whenInsertContent();
    }

    @Test
    public void testValue_Level_NotNegative() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContentWithoutLevel();
        values.putLevel(-1);
        whenInsertContent();
    }

    private void thenEntryIsInserted() {
        assertThat(resultId).isPositive();
    }

    private void whenInsertContent() {
        resultId = categoryRepository.insert(values);
    }

    private void givenMandatoryContent() {
        givenMandatoryContent(true);
    }

    private void givenMandatoryContentWithoutLevel() {
        givenMandatoryContent(false);
    }

    private void givenMandatoryContent(boolean withLevel) {
        values = new CategoryContentValues();
        values.putName("Account Name");
        values.putSection("section A");
        values.putInterval("interval A");
        values.putDirection("some direction");
        if (withLevel) values.putLevel(1);
    }

}