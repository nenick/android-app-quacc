package de.nenick.quacc.database.category;

import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;

import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;
import de.nenick.quacc.database.testsupport.RoboDatabaseTest;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public abstract class CategoryTestBase extends RoboDatabaseTest {

    CategoryRepository categoryRepository;
    CategoryContentValues values;
    long resultId;
    CategoryCursor result;
    List<CategoryCursor> created;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        categoryRepository = CategoryRepository_.getInstance_(context);
    }

    public void givenEntriesCount(int count) {
        created = TestDbData.iNeed(count, CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategorySelection.class, CategoryCursor.class);
    }

    public void thenQueryResultCountIs(int expected) {
        Assertions.assertThat(result).hasCount(expected);
    }
}
