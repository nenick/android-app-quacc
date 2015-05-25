package de.nenick.quacc.database;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryDbTest extends RoboDatabaseTest {

    public static final String testIntervalOnce = "testIntervalOnce";
    public static final String testTypeWeekly = "testTypeWeekly";
    public static final String testTypeAll = "testAll";
    public static final String testIntervalAll = "testAll";
    public static final String testTypeIncoming = "testIncoming";
    public static final String testTypeOutgoing = "testOutgoing";
    CategoryDb categoryDb;

    @Before
    public void setup() {
        categoryDb = CategoryDb_.getInstance_(context);
    }

    @Test
    public void shouldFilterCategories() {
        categoryDb.insert("SectionA", "AllIncoming", testIntervalAll, testTypeIncoming);
        categoryDb.insert("SectionA", "AllOutgoing", testIntervalAll, testTypeOutgoing);
        categoryDb.insert("SectionA", "OnceAll", testIntervalOnce, testTypeAll);
        categoryDb.insert("SectionA", "WeeklyAll", testTypeWeekly, testTypeAll);
        categoryDb.insert("SectionA", "AllAll", testIntervalAll, testTypeAll);
        categoryDb.insert("SectionA", "OnceIncoming", testIntervalOnce, testTypeIncoming);
        categoryDb.insert("SectionA", "OnceOutgoing", testIntervalOnce, testTypeOutgoing);
        categoryDb.insert("SectionA", "WeeklyIncoming", testTypeWeekly, testTypeOutgoing);
        categoryDb.insert("SectionA", "WeeklyOutgoing", testTypeWeekly, testTypeIncoming);

        String[] intervals = {testIntervalOnce, testIntervalAll};
        String[] types = {testTypeIncoming, testTypeAll};
        CategoryCursor categoryResult = categoryDb.getAllFor(intervals, types, CategoryDb.sortBySectionAndName);

        List<String> expected = new ArrayList<>();
        expected.add("AllIncoming");
        expected.add("OnceAll");
        expected.add("AllAll");
        expected.add("OnceIncoming");

        while (categoryResult.moveToNext()) {
            assertThat(categoryResult.getName()).isIn(expected);
        }
        assertThat(categoryResult.getCount()).isEqualTo(expected.size());
    }

    @Test
    public void shouldOrderCategories() {
        categoryDb.insert("SectionB", "A", testIntervalOnce, testTypeIncoming);
        categoryDb.insert("SectionB", "C", testIntervalOnce, testTypeIncoming);
        categoryDb.insert("SectionB", "B", testIntervalOnce, testTypeIncoming);
        categoryDb.insert("SectionA", "A", testIntervalOnce, testTypeIncoming);

        String[] intervals = {testIntervalOnce};
        String[] types = {testTypeIncoming};
        CategoryCursor categoryResult = categoryDb.getAllFor(intervals, types, CategoryDb.sortBySectionAndName);

        assertThatNextResultIs("SectionA", "A", categoryResult);
        assertThatNextResultIs("SectionB", "A", categoryResult);
        assertThatNextResultIs("SectionB", "B", categoryResult);
        assertThatNextResultIs("SectionB", "C", categoryResult);
    }

    private void assertThatNextResultIs(String section, String name, CategoryCursor categoryResult) {
        categoryResult.moveToNext();
        assertThat(categoryResult.getSection()).isEqualTo(section);
        assertThat(categoryResult.getName()).isEqualTo(name);
    }
}