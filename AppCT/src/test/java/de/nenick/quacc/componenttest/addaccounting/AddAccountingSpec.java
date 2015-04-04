package de.nenick.quacc.componenttest.addaccounting;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.TestDateUtil;
import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.AddAccountingFragment;
import de.nenick.quacc.addaccounting.RoboAddAccountingPage;
import de.nenick.quacc.componenttest.RoboComponentTestBase;
import de.nenick.quacc.core.accounting.GetAccountingListUc;
import de.nenick.quacc.core.accounting.GetAccountingListUc_;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingType;
import de.nenick.quacc.database.tools.TestDatabaseDateUtil;
import de.nenick.quacc.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class AddAccountingSpec extends RoboComponentTestBase {

    RoboSup<AddAccountingActivity_, AddAccountingFragment> robo = new RoboSup<>();
    RoboAddAccountingPage addAccountingPage = new RoboAddAccountingPage(robo);
    GetAccountingListUc getAccountingListUc;

    @Before
    public void setUp() {
        getAccountingListUc = GetAccountingListUc_.getInstance_(context);
    }

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
    public void shouldAcceptEmptyComment() {
        addAccountingPage.startPage();
        assertThat(getAccountingListUc.apply().getCount()).isZero();

        addAccountingPage.commentField().setText("");
        addAccountingPage.actionbar().cofirmMenuItem().click();

        AccountingCursor accountings = getAccountingListUc.apply();
        assertThat(accountings.getCount()).isEqualTo(1);
        accountings.moveToFirst();
        assertThat(accountings.getComment()).isEqualTo("");
    }

    @Test
    public void shouldNotAcceptZeroValue() {
        addAccountingPage.startPage();
        assertThat(getAccountingListUc.apply().getCount()).isZero();

        addAccountingPage.valueField().setText("0,00");
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(getAccountingListUc.apply().getCount()).isZero();
    }

    @Test
    public void shouldOnlyAcceptValidValues() {
        addAccountingPage.startPage();
        assertThat(getAccountingListUc.apply().getCount()).isZero();

        addAccountingPage.valueField().setText("aa");
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(getAccountingListUc.apply().getCount()).isZero();

        addAccountingPage.valueField().setText("00");
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(getAccountingListUc.apply().getCount()).isZero();
    }
}
