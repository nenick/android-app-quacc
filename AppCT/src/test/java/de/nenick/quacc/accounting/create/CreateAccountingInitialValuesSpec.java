package de.nenick.quacc.accounting.create;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectricpages.components.RoboSpinnerEntry;

import static de.nenick.quacc.test.TestDateUtil.day;
import static de.nenick.quacc.test.TestDateUtil.month;
import static de.nenick.quacc.test.TestDateUtil.year;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateAccountingInitialValuesSpec extends RoboComponentTestBase {

    RoboSup<CreateAccountingActivity_, CreateAccountingFragment> robo = new RoboSup<>();
    RoboCreateAccountingPage addAccountingPage = new RoboCreateAccountingPage(robo);

    List<RoboSpinnerEntry> entries;

    @Test
    public void shouldHaveCorrectInitialState() {
        addAccountingPage.startPage();

        entries = addAccountingPage.accountSpinner().entries();
        assertThat(entries.get(0).getText()).isEqualTo("Konto");
        assertThat(entries.get(1).getText()).isEqualTo("Sparkonto");
        assertThat(entries.get(2).getText()).isEqualTo("Bar");
        assertThat(entries).hasSize(3);
        assertThat(addAccountingPage.accountSpinner().selectedEntry().getText()).isEqualTo("Konto");

        entries = addAccountingPage.typeSpinner().entries();
        assertThat(entries.get(0).getText()).isEqualTo("Ausgabe");
        assertThat(entries.get(1).getText()).isEqualTo("Einnahme");
        assertThat(entries.get(2).getText()).isEqualTo("Übertrag");
        assertThat(entries).hasSize(3);
        assertThat(addAccountingPage.typeSpinner().selectedEntry().getText()).isEqualTo("Ausgabe");

        entries = addAccountingPage.intervalSpinner().entries();
        assertThat(entries.get(0).getText()).isEqualTo("Einmahlig");
        assertThat(entries.get(1).getText()).isEqualTo("Wöchentlich");
        assertThat(entries.get(2).getText()).isEqualTo("Monatlich");
        assertThat(entries.get(3).getText()).isEqualTo("Alle_3_Monate");
        assertThat(entries).hasSize(4);
        assertThat(addAccountingPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Einmahlig");

        entries = addAccountingPage.categorySpinner().entries();
        assertThat(entries.get(0).getText()).isEqualTo("Beruf");
        assertThat(entries.get(1).getText()).isEqualTo("Essen");
        assertThat(entries.get(2).getText()).isEqualTo("Freizeit");
        assertThat(entries.get(3).getText()).isEqualTo("Miete");
        assertThat(entries).hasSize(4);
        assertThat(addAccountingPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Einmahlig");

        assertThat(addAccountingPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(), month(), year()));
    }

    public Bundle speechResultBundle(String text) {
        ArrayList<String> speechResultText = new ArrayList<>();
        speechResultText.add(text);
        Bundle speechResultBundle = new Bundle();
        speechResultBundle.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, speechResultText);
        return speechResultBundle;
    }

}
