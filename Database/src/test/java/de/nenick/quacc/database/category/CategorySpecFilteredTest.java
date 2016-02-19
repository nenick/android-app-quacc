package de.nenick.quacc.database.category;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;

import static org.assertj.core.api.Assertions.assertThat;

public class CategorySpecFilteredTest extends CategoryTestBase {

    CategorySpecFiltered spec;

    public static final String testIntervalOnce = "testIntervalOnce";
    public static final String testDirectionWeekly = "testDirectionWeekly";
    public static final String testDirectionAll = "testAll";
    public static final String testIntervalAll = "testAll";
    public static final String testDirectionIncoming = "testIncoming";
    public static final String testDirectionOutgoing = "testOutgoing";
    public static final int subCategory = 1;
    public static final int mainCategory = 0;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        String interval = created.get(0).getInterval();
        String direction = created.get(0).getDirection();
        whenQuery(interval, direction);
        thenQueryResultCountIs(1);
    }

    @Test
    public void testFilterCategories() {
        values = new CategoryContentValues();

        categoryRepository.insert(values.putSection("SectionB").putName("SectionB").putInterval(testIntervalAll).putDirection(testDirectionAll).putLevel(mainCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("SectionA").putInterval(testIntervalAll).putDirection(testDirectionAll).putLevel(mainCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("AllIncoming").putInterval(testIntervalAll).putDirection(testDirectionIncoming).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("AllOutgoing").putInterval(testIntervalAll).putDirection(testDirectionOutgoing).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("OnceAll").putInterval(testIntervalOnce).putDirection(testDirectionAll).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("WeeklyAll").putInterval(testDirectionWeekly).putDirection(testDirectionAll).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("AllAll").putInterval(testIntervalAll).putDirection(testDirectionAll).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("OnceIncoming").putInterval(testIntervalOnce).putDirection(testDirectionIncoming).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("OnceOutgoing").putInterval(testIntervalOnce).putDirection(testDirectionOutgoing).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("WeeklyIncoming").putInterval(testDirectionWeekly).putDirection(testDirectionOutgoing).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("WeeklyOutgoing").putInterval(testDirectionWeekly).putDirection(testDirectionIncoming).putLevel(subCategory));

        String[] intervals = {testIntervalOnce, testIntervalAll};
        String[] types = {testDirectionIncoming, testDirectionAll};
        result = categoryRepository.query(new CategorySpecFiltered(intervals, types));

        List<String> expected = new ArrayList<>();
        expected.add("SectionA");
        expected.add("SectionB");
        expected.add("AllIncoming");
        expected.add("OnceAll");
        expected.add("AllAll");
        expected.add("OnceIncoming");

        while (result.moveToNext()) {
            assertThat(result.getName()).isIn(expected);
        }
        assertThat(result.getCount()).isEqualTo(expected.size());
    }

    @Test
    public void testOrderCategories() {
        values = new CategoryContentValues();

        categoryRepository.insert(values.putSection("SectionB").putName("SectionB").putInterval(testIntervalAll).putDirection(testDirectionAll).putLevel(mainCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("SectionA").putInterval(testIntervalAll).putDirection(testDirectionAll).putLevel(mainCategory));

        categoryRepository.insert(values.putSection("SectionB").putName("A").putInterval(testIntervalOnce).putDirection(testDirectionIncoming).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionB").putName("C").putInterval(testIntervalOnce).putDirection(testDirectionIncoming).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionB").putName("B").putInterval(testIntervalOnce).putDirection(testDirectionIncoming).putLevel(subCategory));
        categoryRepository.insert(values.putSection("SectionA").putName("A").putInterval(testIntervalOnce).putDirection(testDirectionIncoming).putLevel(subCategory));

        String[] intervals = {testIntervalOnce, testIntervalAll};
        String[] types = {testDirectionIncoming, testDirectionAll};
        result = categoryRepository.query(new CategorySpecFiltered(intervals, types));

        assertThatNextResultIs("SectionA", "SectionA", result);
        assertThatNextResultIs("SectionA", "A", result);
        assertThatNextResultIs("SectionB", "SectionB", result);
        assertThatNextResultIs("SectionB", "A", result);
        assertThatNextResultIs("SectionB", "B", result);
        assertThatNextResultIs("SectionB", "C", result);
    }

    private void whenQuery(String interval, String direction) {
        spec = new CategorySpecFiltered(new String[]{interval}, new String[]{direction});
        result = categoryRepository.query(spec);
    }

    private void assertThatNextResultIs(String section, String name, CategoryCursor categoryResult) {
        categoryResult.moveToNext();
        assertThat(categoryResult.getSection()).isEqualTo(section);
        assertThat(categoryResult.getName()).isEqualTo(name);
    }
}