package de.nenick.quacc.view.accounting_overview;

import android.os.Environment;

import org.joda.time.DateTime;
import org.junit.Test;
import org.robolectric.Robolectric;

import java.io.File;

import de.nenick.quacc.core.accounting.creation.CreateAccountingFunction_;
import de.nenick.quacc.core.accounting.deletion.DeleteAccountingFunction_;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.database.accounting.AccountingDb;
import de.nenick.quacc.database.accounting.AccountingDb_;
import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingListBackupSpec extends RoboComponentTestBase {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();
    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    @Test
    public void shouldExportData() {
        accountingListPage.startPage();
        accountingListPage.list().count();
        accountingListPage.actionbar().exportData().click();

        File backupDatabase = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/database-quacc.db");
        assertThat(backupDatabase).exists();
    }

    @Test
    public void shouldImportData() {
        accountingListPage.startPage();
        assertThat(accountingListPage.list().count()).isEqualTo(0);
        long accountingId = CreateAccountingFunction_.getInstance_(context).apply("Girokonto", AccountingType.incoming.name(), AccountingInterval.daily.name(), 1, DateTime.now().toDate(), 50, "");
        assertThat(accountingListPage.list().count()).isEqualTo(1);
        accountingListPage.actionbar().exportData().click();

        DeleteAccountingFunction_.getInstance_(context).apply(accountingId);
        assertThat(AccountingDb_.getInstance_(context).getAll().getCount()).isEqualTo(0);

        assertThat(accountingListPage.list().count()).isEqualTo(0);

        accountingListPage.actionbar().importData().click();
        assertThat(accountingListPage.list().count()).isEqualTo(1);
    }
}
