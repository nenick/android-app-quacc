package de.nenick.quacc.scenarios;

import org.joda.time.DateTime;
import org.junit.Test;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.i18n.AccountingIntervalTranslator_;
import de.nenick.quacc.i18n.AccountingTypeTranslator_;
import de.nenick.quacc.view.accounting_create.RoboCreateAccountingPage;
import de.nenick.quacc.view.accounting_overview.RoboAccountingListPage;
import de.nenick.robolectric.RoboComponentTestBase;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountingIntervalScenarioSpec extends RoboComponentTestBase {

    RoboCreateAccountingPage roboCreateAccountingPage = new RoboCreateAccountingPage();
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
        roboCreateAccountingPage.startPage();
        roboCreateAccountingPage.intervalSpinner().entry(AccountingIntervalTranslator_.getInstance_(context).translate(AccountingInterval.monthly)).select();
        roboCreateAccountingPage.dateField().setText(QuAccDateUtil.toString(dateTime));
        roboCreateAccountingPage.valueField().setText("5,00");
        roboCreateAccountingPage.actionbar().confirmMenuItem().click();
        roboCreateAccountingPage.finishPage();
    }
    private void thenOverviewForCurrentMonthShowAccounting(int expected) {
        roboAccountingListPage.startPage();
        assertThat(roboAccountingListPage.list().count()).isEqualTo(expected);
        roboAccountingListPage.finishPage();
    }

}
