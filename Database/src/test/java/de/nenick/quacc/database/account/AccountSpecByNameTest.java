package de.nenick.quacc.database.account;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountSpecByNameTest extends AccountTestBase {

    AccountSpecByName spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        String accountName = created.get(0).getName();

        whenQuery(accountName);
        thenQueryResultCountIs(1);
        thenQueryResultContains(accountName);
    }

    private void thenQueryResultContains(String name) {
        assertThat(result.getName()).isEqualTo(name);
    }

    private void whenQuery(String name) {
        spec = new AccountSpecByName(name);
        result = accountRepository.query(spec);
        result.moveToNext();
    }
}