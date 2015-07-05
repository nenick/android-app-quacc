package de.nenick.quacc.database.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDbTest extends RoboDatabaseTest {

    AccountDb accountDb;
    String name = "name";
    int initialValue = 32;
    long id;
    AccountCursor accountCursor;

    @Before
    public void setup() {
        accountDb = AccountDb_.getInstance_(context);
    }

    @After
    public void teardown() {
        if (accountCursor != null) {
            accountCursor.close();
        }
    }

    @Test
    public void insert_shouldAcceptDefaultEntry() {
        whenInsertAccount();
        thenAccountingHasGivenContent();
    }

    @Test
    public void getIdByName() {
        whenInsertAccount();
        name = "other";
        whenInsertAccount();
        assertThat(accountDb.getIdByName(name)).isEqualTo(id);
    }

    @Test
    public void updateInitialValue() {
        whenInsertAccount();
        initialValue = initialValue + 1;
        accountDb.updateInitialValue(name, initialValue);
        thenAccountingHasGivenContent();
    }

    @Test
    public void getAccountByName() {
        whenInsertAccount();
        accountCursor = accountDb.getAccountByName(name);
        assertThat(accountCursor.getCount()).isPositive();
    }

    @Test
    public void deleteAll() {
        whenInsertAccount();
        accountDb.deleteAll();
        accountCursor = accountDb.getAll();
        assertThat(accountCursor.getCount()).isZero();
    }

    private void whenInsertAccount() {
        id = accountDb.insert(name, initialValue);
    }

    private void thenAccountingHasGivenContent() {
        accountCursor = accountDb.getById(id);
        accountCursor.moveToFirst();
        assertThat(accountCursor.getId()).isEqualTo(id);
        assertThat(accountCursor.getName()).isEqualTo(name);
        assertThat(accountCursor.getInitialvalue()).isEqualTo(initialValue);
    }
}
