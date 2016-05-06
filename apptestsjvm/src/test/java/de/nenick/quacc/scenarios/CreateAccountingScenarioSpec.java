package de.nenick.quacc.scenarios;

import org.joda.time.DateTime;
import org.junit.Test;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.core.i18n.AccountingIntervalTranslator_;
import de.nenick.quacc.view.accounting_edit.RoboEditAccountingPage;
import de.nenick.quacc.view.accounting_overview.RoboAccountingListPage;
import de.nenick.quacc.robolectric.RoboComponentTestBase;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountingScenarioSpec extends RoboComponentTestBase {

    RoboEditAccountingPage roboEditAccountingPage = new RoboEditAccountingPage();
    RoboAccountingListPage roboAccountingListPage = new RoboAccountingListPage();

    @Test
    public void testCreateAccountingForToday() {
        whenCreateAccounting(new DateTime());
        thenOverviewForCurrentMonthShowAccounting(1);
    }

    @Test
    public void testCreateAccountingForAnotherMonth() {
        whenCreateAccounting(new DateTime().minusMonths(1));
        thenOverviewForCurrentMonthShowAccounting(0);
    }

    private void whenCreateAccounting(DateTime dateTime) {
        roboEditAccountingPage.startPage();
        roboEditAccountingPage.intervalSpinner().entry(AccountingIntervalTranslator_.getInstance_(context).translate(AccountingInterval.once)).select();
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
