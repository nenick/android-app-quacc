package de.nenick.quacc.componenttest.accountinglist;

import com.getbase.android.forger.Forger;

import org.junit.Test;

import de.nenick.quacc.accountinglist.AccountingListActivity_;
import de.nenick.quacc.accountinglist.AccountingListFragment_;
import de.nenick.quacc.accountinglist.RoboAccountingListPage;
import de.nenick.quacc.RoboComponentTestBase;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.TestDataGraph;
import de.nenick.quacc.database.provider.testdata.base.DataModel;
import de.nenick.quacc.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class InitialAccountingListSpec extends RoboComponentTestBase {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();

    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    Forger<DataModel> access = TestDataGraph.access();

    @Test
    public void shouldShowAccountings() {
        access.iNeed(3).of(Accounting.class)
                .with(AccountingColumns.ACCOUNTING_INTERVAL, 1)
                .with(AccountingColumns.ACCOUNTING_TYPE, 1)
                .in(context.getContentResolver());

        accountingListPage.startPage();
        assertThat(accountingListPage.list().count()).isEqualTo(3);
    }

    /*
        @Test
    public void shoudlShowAccountings() {
        Date date = new Date();
        AccountingCursor accountingCursor = mock(AccountingCursor.class);
        given(accountingCursor.getCount()).willReturn(3);
        given(accountingCursor.moveToPosition(anyInt())).willReturn(true, true, true);
        given(accountingCursor.getAccountingDate()).willReturn(date);
        given(accountingCursor.getAccountingType()).willReturn(AccountingType.Ausgabe);
        given(accountingCursor.getAccountingInterval()).willReturn(AccountingInterval.Einmahlig);
        given(accountingCursor.getComment()).willReturn("", "Eintrag 1", "Eintrag 2", "Eintrag 3");
        given(accountingCursor.getValue()).willReturn(0, 0, 10, 230);
        //TODO given(TestQuAccApplication.coreModuleMocks.getAccountingListUc.apply()).willReturn(accountingCursor);
        accountingListPage.startPage();
        assertThat(accountingListPage.list().count()).isEqualTo(3);

        List<RoboListViewEntry> entries = accountingListPage.list().entries();
        assertThat(entries.get(0).getText(R.id.date)).isEqualTo(QuAccDateFormatUtil.getDefaultDateFormat().format(date));
        assertThat(entries.get(0).getText(R.id.type)).isEqualTo("Ausgabe");
        assertThat(entries.get(0).getText(R.id.interval)).isEqualTo("Einmahlig");
        assertThat(entries.get(0).getText(R.id.category)).isEqualTo("");
        assertThat(entries.get(0).getText(R.id.comment)).isEqualTo("Eintrag 1");
        assertThat(entries.get(0).getText(R.id.value)).isEqualTo("0,00");

        assertThat(entries.get(1).getText(R.id.comment)).isEqualTo("Eintrag 2");
        assertThat(entries.get(1).getText(R.id.value)).isEqualTo("0,10");

        assertThat(entries.get(2).getText(R.id.comment)).isEqualTo("Eintrag 3");
        assertThat(entries.get(2).getText(R.id.value)).isEqualTo("2,30");
    }

    @Test
    public void shouldStartAddAccountingPage() {
        accountingListPage.startPage();
        accountingListPage.addAccountingButton().click();
        assertThat(accountingListPage.nextStartedPage()).isEqualTo(RoboAddAccountingPage.Intent());
    }
     */
}
