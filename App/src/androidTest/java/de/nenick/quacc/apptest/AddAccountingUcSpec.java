package de.nenick.quacc.apptest;

import android.support.test.filters.RequiresDevice;
import android.test.suitebuilder.annotation.Suppress;

import org.junit.Test;

import de.nenick.quacc.apptest.espresso.BaseEspressoSpec;
import de.nenick.quacc.apptest.pages.EspressoAccountingListPage;
import de.nenick.quacc.apptest.pages.EspressoAddAccountingPage;

public class AddAccountingUcSpec extends BaseEspressoSpec {

    EspressoAccountingListPage accountingListPage = new EspressoAccountingListPage();
    EspressoAddAccountingPage addAccountingPage = new EspressoAddAccountingPage();

    @Test
    public void testStartup() {
        startApp();
        accountingListPage.clickAddButton();
        addAccountingPage.isActivePage();

    }

    @Test
    @RequiresDevice
    public void testSpeechRecognition() throws InterruptedException {
        startApp();
        accountingListPage.clickAddButton();
        addAccountingPage.clickSpeechRecogniction();
        Thread.sleep(1000);
        addAccountingPage.clickSpeechRecogniction();
    }
}
