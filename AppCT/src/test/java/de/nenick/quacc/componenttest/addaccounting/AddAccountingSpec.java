package de.nenick.quacc.componenttest.addaccounting;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.TestDateUtil;
import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.AddAccountingFragment;
import de.nenick.quacc.addaccounting.RoboAddAccountingPage;
import de.nenick.quacc.componenttest.RoboComponentTestBase;
import de.nenick.quacc.database.AccountingRepository;
import de.nenick.quacc.database.AccountingRepository_;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingType;
import de.nenick.quacc.database.tools.TestDatabaseDateUtil;
import de.nenick.quacc.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class AddAccountingSpec extends RoboComponentTestBase {

    RoboSup<AddAccountingActivity_, AddAccountingFragment> robo = new RoboSup<>();
    RoboAddAccountingPage addAccountingPage = new RoboAddAccountingPage(robo);
    AccountingRepository accountingRepository;

    @Before
    public void setUp() {
        accountingRepository = AccountingRepository_.getInstance_(context);
    }

    @Test
    public void shouldAddNewAccountingWithGivenInputValues() {
        addAccountingPage.startPage();
        assertThat(accountingRepository.getAccountings().getCount()).isZero();

        addAccountingPage.accountSpinner().entry("Bar").select();
        addAccountingPage.intervalSpinner().entry("Alle_3_Monate").select();
        addAccountingPage.typeSpinner().entry("Einnahme").select();
        addAccountingPage.categorySpinner().entry("Miete").select();
        addAccountingPage.dateField().setText(TestDateUtil.date(21, 12, 2012));
        addAccountingPage.valueField().setText("5,83");
        addAccountingPage.commentField().setText("take the money");

        addAccountingPage.actionbar().cofirmMenuItem().click();

        AccountingCursor accountings = accountingRepository.getAccountings();
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
        assertThat(accountingRepository.getAccountings().getCount()).isZero();

        addAccountingPage.commentField().setText("");
        addAccountingPage.actionbar().cofirmMenuItem().click();

        AccountingCursor accountings = accountingRepository.getAccountings();
        assertThat(accountings.getCount()).isEqualTo(1);
        accountings.moveToFirst();
        assertThat(accountings.getComment()).isEqualTo("");
    }

    @Test
    public void shouldNotAcceptZeroValue() {
        addAccountingPage.startPage();
        assertThat(accountingRepository.getAccountings().getCount()).isZero();

        addAccountingPage.valueField().setText("0,00");
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(accountingRepository.getAccountings().getCount()).isZero();
    }

    @Test
    public void shouldOnlyAcceptValidValues() {
        addAccountingPage.startPage();
        assertThat(accountingRepository.getAccountings().getCount()).isZero();

        addAccountingPage.valueField().setText("aa");
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(accountingRepository.getAccountings().getCount()).isZero();

        addAccountingPage.valueField().setText("00");
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(accountingRepository.getAccountings().getCount()).isZero();
    }
}
