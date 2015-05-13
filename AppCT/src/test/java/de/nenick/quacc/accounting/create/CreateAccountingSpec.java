package de.nenick.quacc.accounting.create;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.TestDateUtil;
import de.nenick.quacc.RoboComponentTestBase;
import de.nenick.quacc.core.accounting.GetAccountingListUc;
import de.nenick.quacc.core.accounting.GetAccountingListUc_;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingType;
import de.nenick.quacc.database.tools.TestDatabaseDateUtil;
import de.nenick.quacc.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountingSpec extends RoboComponentTestBase {

    RoboSup<CreateAccountingActivity_, CreateAccountingFragment> robo = new RoboSup<>();
    RoboCreateAccountingPage addAccountingPage = new RoboCreateAccountingPage(robo);
    GetAccountingListUc getAccountingListUc;

    @Before
    public void setUp() {
        getAccountingListUc = GetAccountingListUc_.getInstance_(context);
    }

    /*
    @Test
    public void shouldShowInitialValues() {
        addAccountingPage.startPage();

        entries = addAccountingPage.accountSpinner().entries();
        assertThat(entries).hasSize(3);

        entries = addAccountingPage.typeSpinner().entries();
        assertThat(entries).hasSize(2);

        entries = addAccountingPage.intervalSpinner().entries();
        assertThat(entries).hasSize(4);

        entries = addAccountingPage.categorySpinner().entries();
        assertThat(entries).hasSize(4);

        assertThat(addAccountingPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(), month(), year()));
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

        addAccountingPage.accountSpinner().entries().get(2).select();
        addAccountingPage.typeSpinner().entries().get(1).select();
        addAccountingPage.intervalSpinner().entries().get(2).select();
        addAccountingPage.categorySpinner().entries().get(3).select();
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
    public void shouldAddAccounting() throws ParseException {
        given(TestQuAccApplication.coreModuleMocks.parseAccountingValueUc.apply(anyString())).willReturn(new ParseAccountingValueUc.Result(ParseAccountingValueUc.ParseResult.Successful));
        addAccountingPage.startPage();

        addAccountingPage.accountSpinner().entries().get(2).select();
        addAccountingPage.typeSpinner().entries().get(1).select();
        addAccountingPage.intervalSpinner().entries().get(2).select();
        addAccountingPage.categorySpinner().entries().get(3).select();
        addAccountingPage.dateField().click();
        addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);
        addAccountingPage.dialog().datePicker().clickOk();
        addAccountingPage.valueField().setText("60.00");
        addAccountingPage.commentField().setText("money money");

        addAccountingPage.actionbar().cofirmMenuItem().click();

        DateFormat df = DatePickerFormatUtil.getDefaultDateFormat();
        verify(TestQuAccApplication.coreModuleMocks.addNewAccountingUc).apply("Bar", "Einnahme", "Monatlich", "Miete", df.parse("21.12.2012"), 0, "money money");
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailAtUnknownActivityResult() {
        AddAccountingFragment addAccountingFragment = new AddAccountingFragment();
        addAccountingFragment.onActivityResult(123456, 0, null);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailAtUnknownDateFormat() {
        given(TestQuAccApplication.coreModuleMocks.parseAccountingValueUc.apply(anyString())).willReturn(new ParseAccountingValueUc.Result(ParseAccountingValueUc.ParseResult.Successful));
        addAccountingPage.startPage();
        addAccountingPage.dateField().setText("2000-12-21");
        addAccountingPage.actionbar().cofirmMenuItem().click();
    }

    @Test
    public void shouldGiveFeedbackForInvalidAccountingValue() {
        addAccountingPage.startPage();
        assertThat(addAccountingPage.valueErrorField().getText()).isEmpty();
        given(TestQuAccApplication.coreModuleMocks.parseAccountingValueUc.apply(anyString())).willReturn(new ParseAccountingValueUc.Result(ParseAccountingValueUc.ParseResult.ZeroValue));
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(addAccountingPage.valueErrorField().getText()).isNotEmpty();
    }
     */

    @Test
    public void shouldAddNewAccountingWithGivenInputValues() {
        addAccountingPage.startPage();
        assertThat(getAccountingListUc.apply().getCount()).isZero();

        addAccountingPage.accountSpinner().entry("Bar").select();
        addAccountingPage.intervalSpinner().entry("Alle_3_Monate").select();
        addAccountingPage.typeSpinner().entry("Einnahme").select();
        addAccountingPage.categorySpinner().entry("Miete").select();
        addAccountingPage.dateField().setText(TestDateUtil.date(21, 12, 2012));
        addAccountingPage.valueField().setText("5,83");
        addAccountingPage.commentField().setText("take the money");

        addAccountingPage.actionbar().cofirmMenuItem().click();

        AccountingCursor accountings = getAccountingListUc.apply();
        assertThat(accountings.getCount()).isEqualTo(1);
        accountings.moveToFirst();
        assertThat(accountings.getAccountName()).isEqualTo("Bar");
        assertThat(accountings.getAccountingInterval()).isEqualTo(AccountingInterval.Alle_3_Monate);
        assertThat(accountings.getAccountingType()).isEqualTo(AccountingType.Einnahme);
        assertThat(accountings.getAccountingCategoryName()).isEqualTo("Miete");
        assertThat(accountings.getAccountingDate()).isEqualTo(TestDatabaseDateUtil.parse(TestDateUtil.date(21, 12, 2012)));
        assertThat(accountings.getValue()).isEqualTo(583);
        assertThat(accountings.getComment()).isEqualTo("take the money");
    }

    @Test
    public void shouldGiveFeedbackForInvalidValues() {
        addAccountingPage.startPage();
        assertThat(getAccountingListUc.apply().getCount()).isZero();

        addAccountingPage.valueField().setText("aa");
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(getAccountingListUc.apply().getCount()).isZero();
        assertThat(addAccountingPage.valueErrorField().getText()).contains("NoValidNumber");

        addAccountingPage.valueField().setText("00");
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(getAccountingListUc.apply().getCount()).isZero();
        assertThat(addAccountingPage.valueErrorField().getText()).contains("ZeroValue");
    }
}
