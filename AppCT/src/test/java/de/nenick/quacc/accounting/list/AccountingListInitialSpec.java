package de.nenick.quacc.accounting.list;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.RoboCreateAccountingPage;
import de.nenick.quacc.accounts.RoboAccountsPage;
import de.nenick.quacc.categories.RoboCategoriesPage;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountingDb_;
import de.nenick.quacc.database.AccountingInterval;
import de.nenick.quacc.database.AccountingType;
import de.nenick.quacc.i18n.MonthTranslator_;
import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectricpages.components.RoboListViewEntry;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingListInitialSpec extends RoboComponentTestBase {

    String defaultYear = "2015";
    String defaultMonth = "Mai";
    Date defaultDate = QuAccDateUtil.toDate("5.5." + defaultYear);

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();
    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    @Test
    public void shouldShowAccounting() {
        givenAnEntryOfEachAccountingType();
        whenPageIsStarted();
        thenFilterShowCurrentMonth();

        whenFilterForGivenTestData();
        assertThat(accountingListPage.list().count()).isEqualTo(3);

        List<RoboListViewEntry> entries = accountingListPage.list().entries();

//        assertThat(entries.get(0).getBackgroundColor().getDrawingCacheBackgroundColor()).isEqualTo((R.color.positiveBackground));
        assertThat(entries.get(0).getText(R.id.date)).isEqualTo(QuAccDateUtil.toString(defaultDate));
        assertThat(entries.get(0).getText(R.id.interval)).isEqualTo("Einmahlig");
        assertThat(entries.get(0).getText(R.id.category)).isEqualTo("Allgemein");
        assertThat(entries.get(0).getText(R.id.comment)).isEqualTo("my comment 1");
        assertThat(entries.get(0).getText(R.id.value)).isEqualTo("0,02");

//        assertThat(entries.get(0).getBackgroundColor()).isEqualTo(R.color.negativeBackground);
        assertThat(entries.get(1).getText(R.id.comment)).isEqualTo("my comment 2");
        assertThat(entries.get(1).getText(R.id.value)).isEqualTo("0,40");

//        assertThat(entries.get(0).getBackgroundColor()).isEqualTo(R.color.neutralBackground);
        assertThat(entries.get(2).getText(R.id.comment)).isEqualTo("my comment 3");
        assertThat(entries.get(2).getText(R.id.value)).isEqualTo("6000,00");

        assertThat(accountingListPage.actionbar().title()).isEqualTo("Übersicht (Girokonto");
    }

    @Test
    public void shouldStartAddAccountingPage() {
        whenPageIsStarted();
        accountingListPage.addAccountingButton().click();
        assertThat(accountingListPage.nextStartedPage()).isEqualTo(RoboCreateAccountingPage.Intent());
    }

    @Test
    public void shouldStartCategoryEditor() {
        whenPageIsStarted();
        accountingListPage.actionbar().categories().click();
        assertThat(accountingListPage.nextStartedPage()).isEqualTo(RoboCategoriesPage.Intent());
    }

    @Test
    public void shouldStartAccountsEditor() {
        whenPageIsStarted();
        accountingListPage.actionbar().accounts().click();
        assertThat(accountingListPage.nextStartedPage()).isEqualTo(RoboAccountsPage.Intent());
    }

    private void whenFilterForGivenTestData() {
        accountingListPage.filterMonth().setText(defaultMonth);
        accountingListPage.filterYear().setText(defaultYear);
    }

    private void thenFilterShowCurrentMonth() {
        MonthTranslator_ monthTranslator = MonthTranslator_.getInstance_(context);
        assertThat(accountingListPage.filterMonth().getText()).isEqualTo(monthTranslator.translate(QuAccDateUtil.currentMonth()));
        assertThat(accountingListPage.filterYear().getText()).isEqualTo(QuAccDateUtil.currentYear());
    }

    private void givenAnEntryOfEachAccountingType() {
        AccountingDb_.getInstance_(context).insert(1, AccountingType.incoming.name(), AccountingInterval.once.name(), 1, defaultDate, "my comment 1", 2);
        AccountingDb_.getInstance_(context).insert(1, AccountingType.outgoing.name(), AccountingInterval.once.name(), 1, defaultDate, "my comment 2", 40);
        AccountingDb_.getInstance_(context).insert(1, AccountingType.transfer.name(), AccountingInterval.once.name(), 1, defaultDate, "my comment 3", 600000);
    }

    private void whenPageIsStarted() {
        accountingListPage.startPage();
    }
}
