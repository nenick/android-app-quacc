package de.nenick.quacc.database;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositoryTest extends RoboDatabaseTest {

    AccountRepository repository;

    @Before
    public void setUp() {
        repository = AccountRepository_.getInstance_(context);
    }

    @Test
    public void shouldReturnAccounts() {
        AccountCursor accounts = repository.getAccounts();
        assertThat(accounts.getCount()).isPositive();
    }
}