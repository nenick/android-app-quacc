package de.nenick.quacc.database.account;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;

import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.testsupport.RoboDatabaseTest;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

import static org.assertj.android.api.Assertions.assertThat;

public abstract class AccountTestBase extends RoboDatabaseTest {

    AccountRepository accountRepository;
    AccountContentValues values;
    long resultId;
    AccountCursor result;
    List<AccountCursor> created;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        accountRepository = AccountRepository_.getInstance_(context);
    }

    public void givenEntriesCount(int count) {
        created = TestDbData.iNeed(count, AccountContentValues.class).in(accountRepository, AccountCursor.class);
    }

    public void thenQueryResultCountIs(int expected) {
        assertThat(result).hasCount(expected);
    }
}
