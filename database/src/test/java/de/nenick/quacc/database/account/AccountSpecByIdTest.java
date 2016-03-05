package de.nenick.quacc.database.account;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountSpecByIdTest extends AccountTestBase {

    AccountSpecById spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long id = created.get(0).getId();

        whenQuery(id);
        thenQueryResultCountIs(1);
        thenQueryResultContains(id);
    }

    private void thenQueryResultContains(long id) {
        assertThat(result.getId()).isEqualTo(id);
    }

    private void whenQuery(long id) {
        spec = new AccountSpecById(id);
        result = accountRepository.query(spec);
        result.moveToNext();
    }
}