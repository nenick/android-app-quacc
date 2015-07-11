package de.nenick.quacc.scenarios;

import org.joda.time.DateTime;
import org.junit.Test;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.core.i18n.AccountingIntervalTranslator_;
import de.nenick.quacc.view.accounting_edit.RoboEditAccountingPage;
import de.nenick.quacc.view.accounting_overview.RoboAccountingListPage;
import de.nenick.robolectric.RoboComponentTestBase;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountingIntervalScenarioSpec extends RoboComponentTestBase {

    RoboEditAccountingPage roboEditAccountingPage = new RoboEditAccountingPage();
    RoboAccountingListPage roboAccountingListPage = new RoboAccountingListPage();

    @Test
    public void testCreateAccountingStartFromLastMonth() {
        whenCreateAccounting(new DateTime().minusMonths(1));
        thenOverviewForCurrentMonthShowAccounting(1);
    }

    @Test
    public void testCreateAccountingStartWithNextMonth() {
        whenCreateAccounting(new DateTime().plusMonths(1));
        thenOverviewForCurrentMonthShowAccounting(0);
    }

    private void whenCreateAccounting(DateTime dateTime) {
        roboEditAccountingPage.startPage();
        roboEditAccountingPage.intervalSpinner().entry(AccountingIntervalTranslator_.getInstance_(context).translate(AccountingInterval.monthly)).select();
        roboEditAccountingPage.dateField().setText(QuAccDateUtil.toString(dateTime));
        roboEditAccountingPage.valueField().setText("5,00");
        roboEditAccountingPage.actionbar().confirmMenuItem().click();
        roboEditAccountingPage.finishPage();
    }
    private void thenOverviewForCurrentMonthShowAccounting(int expected) {
        roboAccountingListPage.startPage();
        assertThat(roboAccountingListPage.list().count()).isEqualTo(expected);
        roboAccountingListPage.finishPage();
    }

}
