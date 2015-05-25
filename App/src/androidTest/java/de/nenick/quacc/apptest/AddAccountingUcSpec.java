package de.nenick.quacc.apptest;

import org.junit.Test;

import de.nenick.quacc.apptest.espresso.BaseEspressoSpec;
import de.nenick.quacc.apptest.pages.EspressoAccountingListPage;
import de.nenick.quacc.apptest.pages.EspressoAddAccountingPage;

public class AddAccountingUcSpec extends BaseEspressoSpec {

    EspressoAccountingListPage accountingListPage = new EspressoAccountingListPage();
    EspressoAddAccountingPage addAccountingPage = new EspressoAddAccountingPage();

    @Test
    public void shouldAddNewAccounting() {
        //accountingListPage.list().doNotContain("Monatlich");
        startApp();
        accountingListPage.clickAddButton();
        addAccountingPage.isActivePage();
        addAccountingPage.chooseAccount("Bar");
        addAccountingPage.chooseAccountingInterval("Monatlich");
        addAccountingPage.chooseAccountingType("Einnahme");
        addAccountingPage.chooseAccountingCategory("Mieteinnahmen");
        addAccountingPage.chooseAccountingDate("10.11.2012");
        addAccountingPage.chooseAccountingValue("3,50");
        addAccountingPage.actionbar().clickConfirmButton();

        accountingListPage.isActivePage();
        //accountingListPage.list().doesContain("Monatlich");
    }

    @Test
    public void shouldToggleSpeechRecognition() throws InterruptedException {
        startApp();
        accountingListPage.clickAddButton();
        // check toggle speech recognition on and off
        addAccountingPage.clickSpeechRecogniction();
        addAccountingPage.clickSpeechRecogniction();
    }
}
