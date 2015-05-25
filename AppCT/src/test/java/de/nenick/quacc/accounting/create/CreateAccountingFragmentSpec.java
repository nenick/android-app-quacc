package de.nenick.quacc.accounting.create;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountingDb;
import de.nenick.quacc.database.AccountingDb_;
import de.nenick.quacc.database.AccountingInterval;
import de.nenick.quacc.database.AccountingType;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.test.TestDateUtil;
import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;

import static de.nenick.quacc.test.TestDateUtil.day;
import static de.nenick.quacc.test.TestDateUtil.month;
import static de.nenick.quacc.test.TestDateUtil.year;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountingFragmentSpec extends RoboComponentTestBase {

    RoboSup<CreateAccountingActivity_, CreateAccountingFragment> robo = new RoboSup<>();
    RoboCreateAccountingPage addAccountingPage = new RoboCreateAccountingPage(robo);
    AccountingDb accountingDb;

    @Before
    public void setUp() {
        accountingDb = AccountingDb_.getInstance_(context);
    }

    @Test
    public void shouldShowValueFromDatePicker() {
        addAccountingPage.startPage();
        addAccountingPage.dateField().click();
        assertThat(addAccountingPage.dialog().datePicker().isShowing()).isTrue();
        addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);
        addAccountingPage.dialog().datePicker().clickOk();
        assertThat(addAccountingPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(21), month(12), year(2012)));
    }

    @Test
    public void shouldSaveStateForConfigChanges() {
        addAccountingPage.startPage();

        addAccountingPage.accountSpinner().entry("Bar").select();;
        addAccountingPage.typeSpinner().entry("Einnahme").select();
        addAccountingPage.intervalSpinner().entry("Monatlich").select();
        addAccountingPage.categorySpinner().entry("Miete").select();
        addAccountingPage.dateField().click();
        addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);
        addAccountingPage.dialog().datePicker().clickOk();
        addAccountingPage.valueField().setText("60.00");

        robo.activityController.restart();

        assertThat(addAccountingPage.accountSpinner().selectedEntry().getText()).isEqualTo("Bar");
        assertThat(addAccountingPage.typeSpinner().selectedEntry().getText()).isEqualTo("Einnahme");
        assertThat(addAccountingPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Monatlich");
        assertThat(addAccountingPage.categorySpinner().selectedEntry().getText()).isEqualTo("Miete");
        assertThat(addAccountingPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(21), month(12), year(2012)));
        assertThat(addAccountingPage.valueField().getText()).isEqualTo("60.00");
    }

    @Test
    public void shouldAddAccounting()  {
        addAccountingPage.startPage();

        addAccountingPage.accountSpinner().entries().get(2).select();
        addAccountingPage.typeSpinner().entries().get(1).select();
        addAccountingPage.intervalSpinner().entries().get(2).select();
        addAccountingPage.categorySpinner().entries().get(0).select();
        addAccountingPage.dateField().click();
        addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);
        addAccountingPage.dialog().datePicker().clickOk();
        addAccountingPage.valueField().setText("60,00");
        addAccountingPage.commentField().setText("money money");

        addAccountingPage.actionbar().confirmMenuItem().click();

        assertThat(AccountingDb_.getInstance_(context).getAll().getCount()).isEqualTo(1);
    }

    @Test
    public void shouldAddNewAccountingWithGivenInputValues() {
        addAccountingPage.startPage();
        assertThat(accountingDb.getAll().getCount()).isZero();

        addAccountingPage.accountSpinner().entry("Bar").select();
        addAccountingPage.intervalSpinner().entry("Alle 3 Monate").select();
        addAccountingPage.typeSpinner().entry("Einnahme").select();
        addAccountingPage.categorySpinner().entry("Miete").select();
        addAccountingPage.dateField().setText(TestDateUtil.date(21, 12, 2012));
        addAccountingPage.valueField().setText("5,83");
        addAccountingPage.commentField().setText("take the money");

        addAccountingPage.actionbar().confirmMenuItem().click();

        AccountingCursor accountings = accountingDb.getAll();
        assertThat(accountings.getCount()).isEqualTo(1);
        accountings.moveToFirst();
        assertThat(accountings.getAccountName()).isEqualTo("Bar");
        assertThat(accountings.getInterval()).isEqualTo(AccountingInterval.eachThirdMonth.name());
        assertThat(accountings.getType()).isEqualTo(AccountingType.incoming.name());
        assertThat(accountings.getCategoryName()).isEqualTo("Miete");
        assertThat(accountings.getDate()).isEqualTo(QuAccDateUtil.parse(TestDateUtil.date(21, 12, 2012)));
        assertThat(accountings.getValue()).isEqualTo(583);
        assertThat(accountings.getComment()).isEqualTo("take the money");
    }

    @Test
    public void shouldGiveFeedbackForInvalidValues() {
        addAccountingPage.startPage();

        whenValueIs("A");
        thenHintIsShown("Zugelassene zeichen sind 0 bis 9 und \",\"");

        whenValueIs("30,30,30");
        thenHintIsShown("Nutze als Format z.B. 4500,00");

        whenValueIs("");
        thenHintIsShown("Gebe noch einen Betrag an");

        whenValueIs("0");
        thenHintIsShown("Gebe noch einen Betrag an");
    }

    private void thenHintIsShown(String noValidNumber) {
        assertThat(addAccountingPage.valueErrorField().getText()).contains(noValidNumber);
        accountingDb = AccountingDb_.getInstance_(context);
        assertThat(accountingDb.getAll().getCount()).isEqualTo(0);
    }

    private void whenValueIs(String value) {
        addAccountingPage.valueField().setText(value);
        addAccountingPage.actionbar().confirmMenuItem().click();
    }
}
