package de.nenick.quacc.database.category;

import org.junit.Test;

public class CategorySpecAllTest extends CategoryTestBase {

    CategorySpecAll spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new CategorySpecAll();
        result = categoryRepository.query(spec);
    }
}