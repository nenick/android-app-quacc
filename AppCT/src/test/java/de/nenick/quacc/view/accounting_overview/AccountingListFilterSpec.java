package de.nenick.quacc.view.accounting_overview;

import org.junit.Test;

import de.nenick.quacc.R;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountingDb_;
import de.nenick.quacc.database.AccountingInterval;
import de.nenick.quacc.database.AccountingType;
import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingListFilterSpec extends RoboComponentTestBase {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();

    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    @Test
    public void shouldOnlyShowAccountingFromSelectedMonth() {
        givenAccountingFor(1, 4, 2014);
        givenAccountingFor(1, 4, 2015);
        givenAccountingFor(1, 5, 2014);
        givenAccountingFor(1, 5, 2015);
        givenAccountingFor(1, 5, 2013);
        givenAccountingFor(1, 6, 2014);

        whenShowPageFor("Mai", "2014");
        thenOnlyEntryShownIsFrom("1.5.2014");

        whenNavigateMonthDown();
        thenOnlyEntryShownIsFrom("1.4.2014");

        whenNavigateYearUp();
        thenOnlyEntryShownIsFrom("1.4.2015");

        whenNavigateMonthUp();
        thenOnlyEntryShownIsFrom("1.5.2015");

        whenNavigateYearDown();
        thenOnlyEntryShownIsFrom("1.5.2014");
    }

    private void whenNavigateYearDown() {
        accountingListPage.filterYearDown().click();
    }

    private void whenNavigateYearUp() {
        accountingListPage.filterYearUp().click();
    }

    private void whenNavigateMonthDown() {
        accountingListPage.filterMonthDown().click();
    }

    private void whenNavigateMonthUp() {
        accountingListPage.filterMonthUp().click();
    }

    private void thenOnlyEntryShownIsFrom(String comment) {
        assertThat(accountingListPage.list().count()).isEqualTo(1);
        assertThat(accountingListPage.list().entries().get(0).getText(R.id.comment)).isEqualTo(comment);
    }

    private void whenShowPageFor(String month, String year) {
        accountingListPage.startPage();
        accountingListPage.filterMonth().setText(month);
        accountingListPage.filterYear().setText(year);
    }

    private void givenAccountingFor(int day, int month, int year) {
        AccountingDb_.getInstance_(context).insert(1, AccountingType.incoming.name(), AccountingInterval.once.name(), 1, QuAccDateUtil.toDate(day, month, year), day + "." + month + "." + year, 20);
    }
}
