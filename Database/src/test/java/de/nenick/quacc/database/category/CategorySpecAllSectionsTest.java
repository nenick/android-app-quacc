package de.nenick.quacc.database.category;

import org.junit.Test;

public class CategorySpecAllSectionsTest extends CategoryTestBase {

    CategorySpecAllSections spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new CategorySpecAllSections();
        result = categoryRepository.query(spec);
    }

}