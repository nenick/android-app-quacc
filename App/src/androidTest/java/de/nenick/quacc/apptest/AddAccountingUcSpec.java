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
        startApp();
        // TODO check first expected list count
        accountingListPage.clickAddButton();
        addAccountingPage.isActivePage();
        addAccountingPage.chooseAccount("Bar");
        addAccountingPage.chooseAccountingInterval("Monatlich");
        addAccountingPage.chooseAccountingType("Einnahme");
        addAccountingPage.chooseAccountingCategory("Miete");
        addAccountingPage.chooseAccountingDate("10.11.2012");
        addAccountingPage.chooseAccountingValue("3,50");
        // TODO need a confirm button ...
        // TODO check new expected list count
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
