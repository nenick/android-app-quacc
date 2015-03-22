package de.nenick.quacc.componenttest.accountinglist;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.nenick.quacc.accountinglist.AccountingListActivity_;
import de.nenick.quacc.accountinglist.AccountingListFragment;
import de.nenick.quacc.accountinglist.AccountingListFragment_;
import de.nenick.quacc.accountinglist.RoboAccountingListPage;
import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.AddAccountingFragment;
import de.nenick.quacc.addaccounting.RoboAddAccountingPage;
import de.nenick.quacc.componenttest.BaseCT;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectricpages.components.RoboSpinnerEntry;
import de.nenick.robolectricpages.dialogs.RoboDatePickerDialog;

import static org.assertj.core.api.Assertions.assertThat;

public class IntitialSpec extends BaseCT {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();

    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    @Test
    public void shouldShowAcountings() {
        accountingListPage.startPage();
        assertThat(accountingListPage.list().count()).isEqualTo(3);
    }
}
