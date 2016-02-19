package de.nenick.quacc.database.account;

import org.junit.Test;

public class AccountSpecAllTest extends AccountTestBase {

    AccountSpecAll spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new AccountSpecAll();
        result = accountRepository.query(spec);
    }
}