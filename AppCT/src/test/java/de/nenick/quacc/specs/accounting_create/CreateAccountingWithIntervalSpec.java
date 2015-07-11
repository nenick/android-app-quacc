package de.nenick.quacc.specs.accounting_create;

import android.app.Application;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import de.nenick.quacc.core.accounting.creation.CreateAccountingFromIntervalFunction_;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.accounting.AccountingDb;
import de.nenick.quacc.database.accounting.AccountingDb_;
import de.nenick.quacc.database.interval.IntervalAccountingDb_;
import de.nenick.quacc.database.interval.IntervalDb_;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.interval.IntervalCursor;
import de.nenick.quacc.view.accounting_edit.RoboEditAccountingPage;
import de.nenick.robolectric.RoboComponentTestBase;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountingWithIntervalSpec extends RoboComponentTestBase {

    RoboEditAccountingPage addAccountingPage = new RoboEditAccountingPage();
    AccountingDb accountingDb;

    final String defaultDate = "21.12.2012";
    final String updateUntilDate = "23.12.2012";
    private Application context;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application;
        accountingDb = AccountingDb_.getInstance_(context);
    }

    @Test
    public void shouldShowValueFromEndDatePicker() {
        addAccountingPage.startPage();
        whenPickEndDate(defaultDate);
        assertThat(addAccountingPage.endDateField().getText()).isEqualTo(defaultDate);
    }

    @Test
    public void shouldAddIntervalAccounting() {
        addAccountingPage.startPage();

        addAccountingPage.intervalSpinner().entry("Täglich").select();
        givenDate(defaultDate);
        addAccountingPage.valueField().setText("60,00");

        addAccountingPage.actionbar().confirmMenuItem().click();
        andUpdateIntervalDataAtDatabase();

        assertThat(IntervalDb_.getInstance_(context).getAll().getCount()).isEqualTo(1);
        assertThat(AccountingDb_.getInstance_(context).getAll().getCount()).isEqualTo(3);
        assertThat(IntervalAccountingDb_.getInstance_(context).getAll().getCount()).isEqualTo(3);
    }

    @Test
    public void shouldAddNewAccountingWithGivenInputValues() {
        addAccountingPage.startPage();
        assertThat(accountingDb.getAll().getCount()).isZero();

        addAccountingPage.accountSpinner().entry("Bar").select();
        addAccountingPage.intervalSpinner().entry("Alle 3 Monate").select();
        addAccountingPage.typeSpinner().entry("Einnahme").select();
        addAccountingPage.categorySpinner().entry("eBay").select();
        givenDate(defaultDate);
        addAccountingPage.valueField().setText("5,83");
        addAccountingPage.commentField().setText("take the money");

        addAccountingPage.actionbar().confirmMenuItem().click();
        andUpdateIntervalDataAtDatabase();

        AccountingCursor accounting = accountingDb.getAll();
        assertThat(accounting.getCount()).isEqualTo(1);
        accounting.moveToFirst();
        assertThat(accounting.getAccountName()).isEqualTo("Bar");
        assertThat(accounting.getInterval()).isEqualTo(AccountingInterval.eachThirdMonth.name());
        assertThat(accounting.getType()).isEqualTo(AccountingType.incoming.name());
        assertThat(accounting.getCategoryName()).isEqualTo("eBay");
        assertThat(accounting.getDate()).isEqualTo(QuAccDateUtil.toDate(defaultDate));
        assertThat(accounting.getValue()).isEqualTo(583);
        assertThat(accounting.getComment()).isEqualTo("take the money");
    }

    private void andUpdateIntervalDataAtDatabase() {
        IntervalCursor intervalCursor = IntervalDb_.getInstance_(context).getAll();
        intervalCursor.moveToFirst();
        CreateAccountingFromIntervalFunction_.getInstance_(context).apply(intervalCursor, new DateTime(QuAccDateUtil.toDate(updateUntilDate)));
    }

    private void givenDate(String date) {
        addAccountingPage.dateField().click();
        assertThat(addAccountingPage.dialog().datePicker().isShowing()).isTrue();

        // since updateOpenCloseState from robolectric 3.0-rc2 to rc3 picking the date value in long running tests
        // the issue is tracked at https://github.com/robolectric/robolectric/issues/1838
        //addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);

        addAccountingPage.dialog().datePicker().clickOk();

        // instead of picking the date whe set it direct until the issue is fixed
        addAccountingPage.dateField().setText(date);
    }

    private void whenPickEndDate(String date) {
        addAccountingPage.endDateField().click();
        assertThat(addAccountingPage.dialog().datePicker().isShowing()).isTrue();

        // since updateOpenCloseState from robolectric 3.0-rc2 to rc3 picking the date value in long running tests
        // the issue is tracked at https://github.com/robolectric/robolectric/issues/1838
        //addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);

        addAccountingPage.dialog().datePicker().clickOk();

        // instead of picking the date whe set it direct until the issue is fixed
        addAccountingPage.endDateField().setText(date);
    }
}
