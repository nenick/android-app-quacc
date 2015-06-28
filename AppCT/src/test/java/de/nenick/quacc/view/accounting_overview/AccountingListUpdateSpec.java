package de.nenick.quacc.view.accounting_overview;

import org.joda.time.DateTime;
import org.junit.Test;

import de.nenick.quacc.core.accounting.creation.CreateAccountingFunction_;
import de.nenick.quacc.core.accounting.deletion.DeleteAccountingFunction_;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.database.accounting.AccountingDb_;
import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingListUpdateSpec extends RoboComponentTestBase {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();
    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    long accountingId;
    long accountingId2;

    @Test
    public void shouldUpdateIfLastEntryIsDeleted() {
        accountingListPage.startPage();
        thenListHasEntryCount(0);
        givenOneEntry();
        thenListHasEntryCount(1);
        whenEntryGetsDeleted(accountingId);
        thenDatabaseHasEntryCount(0);
        thenListHasEntryCount(0);
    }

    @Test
    public void shouldUpdateIfEntryIsDeleted() {
        accountingListPage.startPage();
        thenListHasEntryCount(0);
        givenTwoEntries();
        thenListHasEntryCount(2);
        whenEntryGetsDeleted(accountingId);
        thenDatabaseHasEntryCount(1);
        thenListHasEntryCount(1);
    }

    private void givenOneEntry() {
        assertThat(accountingListPage.list().count()).isEqualTo(0);
        accountingId = CreateAccountingFunction_.getInstance_(context).apply("Girokonto", AccountingType.incoming.name(), AccountingInterval.daily.name(), 1, DateTime.now().toDate(), 50, "");
        assertThat(accountingListPage.list().count()).isEqualTo(1);
    }

    private void thenListHasEntryCount(int expectedCount) {
        assertThat(accountingListPage.list().count()).isEqualTo(expectedCount);
    }

    private void thenDatabaseHasEntryCount(int expectedCount) {
        assertThat(AccountingDb_.getInstance_(context).getAll().getCount()).isEqualTo(expectedCount);
    }

    private void whenEntryGetsDeleted(long accountingId) {
        DeleteAccountingFunction_.getInstance_(context).apply(accountingId);
    }

    private void givenTwoEntries() {
        assertThat(accountingListPage.list().count()).isEqualTo(0);
        accountingId = CreateAccountingFunction_.getInstance_(context).apply("Girokonto", AccountingType.incoming.name(), AccountingInterval.daily.name(), 1, DateTime.now().toDate(), 50, "");
        accountingId2 = CreateAccountingFunction_.getInstance_(context).apply("Girokonto", AccountingType.incoming.name(), AccountingInterval.daily.name(), 1, DateTime.now().toDate(), 150, "");
        assertThat(accountingListPage.list().count()).isEqualTo(2);
    }
}
