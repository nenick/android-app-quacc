package de.nenick.quacc.apptest;

import org.junit.Test;

import de.nenick.quacc.R;
import de.nenick.quacc.apptest.espresso.BaseEspressoSpec;
import de.nenick.quacc.apptest.pages.EspressoAccountingListPage;
import de.nenick.quacc.apptest.pages.EspressoAddAccountingPage;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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
        addAccountingPage.chooseAccountingCategory("Miete");
        addAccountingPage.chooseAccountingDate("10.11.2012");
        addAccountingPage.chooseAccountingValue("3,50");
        addAccountingPage.actionbar().clickConfirmButton();

        accountingListPage.list().doesContain("Monatlich");
    }

    @Test
    public void testActionBarOverflow() {
        startApp();
        accountingListPage.clickAddButton();
        addAccountingPage.isActivePage();
        addAccountingPage.chooseAccount("Bar");
        addAccountingPage.chooseAccountingInterval("Monatlich");
        addAccountingPage.chooseAccountingType("Einnahme");
        addAccountingPage.chooseAccountingCategory("Miete");
        addAccountingPage.chooseAccountingDate("10.11.2012");
        addAccountingPage.chooseAccountingValue("3,50");

        openActionBarOverflowOrOptionsMenu(main.instrumentation().getTargetContext());

        onView(withText("Fertig"))
                .perform(click());

        accountingListPage.list().doesContain("Monatlich");
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
