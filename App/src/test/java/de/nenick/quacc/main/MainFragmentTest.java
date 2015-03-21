package de.nenick.quacc;

import android.content.Intent;

import org.junit.Test;

import de.nenick.quacc.accountinglist.AccountingListActivity_;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RobolectricSupportedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class MainFragmentTest extends RobolectricSupportedTest {

    RoboSup<MainActivity_, MainFragment_> robo = new RoboSup<>();
    RoboMainPage mainPage = new RoboMainPage(robo);

    @Test
    public void shouldStartNextPage(){
        mainPage.startPage();
        Intent intent = mainPage.nextStartedPage();

        assertThat(intent.getComponent().getClassName()).isEqualTo(AccountingListActivity_.class.getName());
    }
}