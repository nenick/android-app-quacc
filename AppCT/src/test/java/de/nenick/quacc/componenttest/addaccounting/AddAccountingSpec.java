package de.nenick.quacc.componenttest.addaccounting;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.AddAccountingFragment;
import de.nenick.quacc.addaccounting.RoboAddAccountingPage;
import de.nenick.quacc.componenttest.RoboComponentTestBase;
import de.nenick.quacc.database.AccountingRepository;
import de.nenick.quacc.database.AccountingRepository_;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.robolectricpages.components.RoboSpinnerEntry;
import de.nenick.robolectricpages.dialogs.RoboDatePickerDialog;

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
    public void shouldHaveCorrectInitialState() {
        addAccountingPage.startPage();
        assertThat(accountingRepository.getAccountings().getCount()).isZero();
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(accountingRepository.getAccountings().getCount()).isEqualTo(1);
    }

    public Bundle speechResultBundle(String text) {
        ArrayList<String> speechResultText = new ArrayList<>();
        speechResultText.add(text);
        Bundle speechResultBundle = new Bundle();
        speechResultBundle.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, speechResultText);
        return speechResultBundle;
    }
}
