package de.nenick.quacc.apptest;

import de.nenick.quacc.apptest.espresso.BaseEspressoSpec;
import de.nenick.quacc.apptest.pages.EspressoAccountingListPage;
import de.nenick.quacc.apptest.pages.EspressoAddAccountingPage;

public class AddAccountingUcSpec extends BaseEspressoSpec {

    EspressoAccountingListPage accountingListPage = new EspressoAccountingListPage();
    EspressoAddAccountingPage addAccountingPage = new EspressoAddAccountingPage();

    public void testStartup() {
        startApp();
        accountingListPage.clickAddButton();
        addAccountingPage.isActivePage();

        addAccountingPage.clickSpeechRecogniction();
        addAccountingPage.clickSpeechRecogniction();
    }
}
