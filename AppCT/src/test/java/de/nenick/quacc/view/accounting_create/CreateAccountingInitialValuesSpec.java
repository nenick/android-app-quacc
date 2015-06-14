package de.nenick.quacc.view.accounting_create;

import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.view.View;

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
        assertThat(entries.get(0).getText()).isEqualTo("Girokonto");
        assertThat(entries.get(1).getText()).isEqualTo("Bar");
        assertThat(entries.get(2).getText()).isEqualTo("Tagesgeldkonto");
        assertThat(entries).hasSize(3);
        assertThat(addAccountingPage.accountSpinner().selectedEntry().getText()).isEqualTo("Girokonto");

        entries = addAccountingPage.typeSpinner().entries();
        assertThat(entries.get(0).getText()).isEqualTo("Einnahme");
        assertThat(entries.get(1).getText()).isEqualTo("Ausgabe");
        assertThat(entries.get(2).getText()).isEqualTo("Übertrag");
        assertThat(entries).hasSize(3);
        assertThat(addAccountingPage.typeSpinner().selectedEntry().getText()).isEqualTo("Ausgabe");

        assertThat(addAccountingPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(), month(), year()));

        entries = addAccountingPage.intervalSpinner().entries();
        assertThat(entries.get(0).getText()).isEqualTo("Einmahlig");
        assertThat(entries.get(1).getText()).isEqualTo("Täglich");
        assertThat(entries.get(2).getText()).isEqualTo("Wöchentlich");
        assertThat(entries.get(3).getText()).isEqualTo("Alle 2 Wochen");
        assertThat(entries.get(4).getText()).isEqualTo("Monatlich");
        assertThat(entries.get(5).getText()).isEqualTo("Alle 2 Monate");
        assertThat(entries.get(6).getText()).isEqualTo("Alle 3 Monate");
        assertThat(entries.get(7).getText()).isEqualTo("Alle 6 Monate");
        assertThat(entries.get(8).getText()).isEqualTo("Jährlich");
        assertThat(entries).hasSize(9);
        assertThat(addAccountingPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Einmahlig");

        entries = addAccountingPage.categorySpinner().entries();
        assertThat(entries.size()).isPositive();

        assertThat(addAccountingPage.endDateField().getVisibility()).isEqualTo(View.INVISIBLE);
        assertThat(addAccountingPage.endDateField().getText()).isEqualTo(String.format("%s.%s.%s", day(), month(), year()));
    }

    public Bundle speechResultBundle(String text) {
        ArrayList<String> speechResultText = new ArrayList<>();
        speechResultText.add(text);
        Bundle speechResultBundle = new Bundle();
        speechResultBundle.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, speechResultText);
        return speechResultBundle;
    }

}
