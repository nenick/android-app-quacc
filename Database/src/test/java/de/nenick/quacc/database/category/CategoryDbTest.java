package de.nenick.quacc.database.category;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryDbTest extends RoboDatabaseTest {

    public static final String testIntervalOnce = "testIntervalOnce";
    public static final String testTypeWeekly = "testTypeWeekly";
    public static final String testTypeAll = "testAll";
    public static final String testIntervalAll = "testAll";
    public static final String testTypeIncoming = "testIncoming";
    public static final String testTypeOutgoing = "testOutgoing";
    public static final int subCategory = 1;
    public static final int mainCategory = 0;
    CategoryDb categoryDb;
    CategoryCursor categoryResult;

    @Before
    public void setup() {
        categoryDb = CategoryDb_.getInstance_(context);
    }

    @After
    public void teardown() {
        if(categoryResult != null) {
            categoryResult.close();
        }
    }

    @Test
    public void getAllFor_shouldFilterCategories() {
        categoryDb.insert("SectionB", "SectionB", testIntervalAll, testTypeAll, mainCategory);
        categoryDb.insert("SectionA", "SectionA", testIntervalAll, testTypeAll, mainCategory);
        categoryDb.insert("SectionA", "AllIncoming", testIntervalAll, testTypeIncoming, subCategory);
        categoryDb.insert("SectionA", "AllOutgoing", testIntervalAll, testTypeOutgoing, subCategory);
        categoryDb.insert("SectionA", "OnceAll", testIntervalOnce, testTypeAll, subCategory);
        categoryDb.insert("SectionA", "WeeklyAll", testTypeWeekly, testTypeAll, subCategory);
        categoryDb.insert("SectionA", "AllAll", testIntervalAll, testTypeAll, subCategory);
        categoryDb.insert("SectionA", "OnceIncoming", testIntervalOnce, testTypeIncoming, subCategory);
        categoryDb.insert("SectionA", "OnceOutgoing", testIntervalOnce, testTypeOutgoing, subCategory);
        categoryDb.insert("SectionA", "WeeklyIncoming", testTypeWeekly, testTypeOutgoing, subCategory);
        categoryDb.insert("SectionA", "WeeklyOutgoing", testTypeWeekly, testTypeIncoming, subCategory);

        String[] intervals = {testIntervalOnce, testIntervalAll};
        String[] types = {testTypeIncoming, testTypeAll};
        categoryResult = categoryDb.getAllFor(intervals, types, CategoryDb.sortBySectionAndName);

        List<String> expected = new ArrayList<>();
        expected.add("SectionA");
        expected.add("SectionB");
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
    public void getAllFor_shouldOrderCategories() {
        categoryDb.insert("SectionB", "SectionB", testIntervalAll, testTypeAll, mainCategory);
        categoryDb.insert("SectionA", "SectionA", testIntervalAll, testTypeAll, mainCategory);
        categoryDb.insert("SectionB", "A", testIntervalOnce, testTypeIncoming, subCategory);
        categoryDb.insert("SectionB", "C", testIntervalOnce, testTypeIncoming, subCategory);
        categoryDb.insert("SectionB", "B", testIntervalOnce, testTypeIncoming, subCategory);
        categoryDb.insert("SectionA", "A", testIntervalOnce, testTypeIncoming, subCategory);

        String[] intervals = {testIntervalOnce, testIntervalAll};
        String[] types = {testTypeIncoming, testTypeAll};
        categoryResult = categoryDb.getAllFor(intervals, types, CategoryDb.sortBySectionAndName);

        assertThatNextResultIs("SectionA", "SectionA", categoryResult);
        assertThatNextResultIs("SectionA", "A", categoryResult);
        assertThatNextResultIs("SectionB", "SectionB", categoryResult);
        assertThatNextResultIs("SectionB", "A", categoryResult);
        assertThatNextResultIs("SectionB", "B", categoryResult);
        assertThatNextResultIs("SectionB", "C", categoryResult);
    }

    @Test
    public void getAll() {
        categoryDb.insert("SectionB", "SectionB", testIntervalAll, testTypeAll, mainCategory);
        categoryResult = categoryDb.getAll();
        assertThat(categoryResult.getCount()).isPositive();
    }

    @Test
    public void getAllSections() {
        categoryDb.insert("SectionB", "SectionB", testIntervalAll, testTypeAll, mainCategory);
        categoryDb.insert("SectionA", "SectionA", testIntervalAll, testTypeAll, mainCategory);
        categoryDb.insert("SectionB", "A", testIntervalOnce, testTypeIncoming, subCategory);
        categoryDb.insert("SectionB", "C", testIntervalOnce, testTypeIncoming, subCategory);
        categoryResult = categoryDb.getAllSections();
        assertThat(categoryResult.getCount()).isEqualTo(2);
    }

    @Test
    public void existSection() {
        categoryDb.insert("SectionA", "SectionA", testIntervalAll, testTypeAll, mainCategory);
        assertThat(categoryDb.existSection("SectionA")).isTrue();
        assertThat(categoryDb.existSection("SectionB")).isFalse();
    }

    @Test
    public void deleteAll() {
        categoryDb.insert("SectionA", "SectionA", testIntervalAll, testTypeAll, mainCategory);
        categoryDb.deleteAll();
        categoryResult = categoryDb.getAll();
        assertThat(categoryResult.getCount()).isZero();
    }

    private void assertThatNextResultIs(String section, String name, CategoryCursor categoryResult) {
        categoryResult.moveToNext();
        assertThat(categoryResult.getSection()).isEqualTo(section);
        assertThat(categoryResult.getName()).isEqualTo(name);
    }
}