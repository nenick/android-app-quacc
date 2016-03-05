package de.nenick.quacc.database.category;

import org.junit.Test;

public class CategorySpecBySectionsTest extends CategoryTestBase {

    CategorySpecBySections spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        String section = created.get(0).getSection();
        whenQuery(section);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(String section) {
        spec = new CategorySpecBySections(section);
        result = categoryRepository.query(spec);
    }

}