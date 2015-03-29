package de.nenick.quacc.componenttest.addaccounting;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.AddAccountingFragment;
import de.nenick.quacc.addaccounting.RoboAddAccountingPage;
import de.nenick.quacc.componenttest.RoboComponentTestBase;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.robolectricpages.components.RoboSpinnerEntry;
import de.nenick.robolectricpages.dialogs.RoboDatePickerDialog;

import static org.assertj.core.api.Assertions.assertThat;

public class InitialAddAccountingSpec extends RoboComponentTestBase {

    RoboSup<AddAccountingActivity_, AddAccountingFragment> robo = new RoboSup<>();

    RoboAddAccountingPage addAccountingPage = new RoboAddAccountingPage(robo);
    RoboDatePickerDialog datePickerDialog = new RoboDatePickerDialog();

    List<RoboSpinnerEntry> entries;
    Calendar calendar = Calendar.getInstance(Locale.GERMAN);

    @Test
    public void shouldHaveCorrectInitialState() {
        addAccountingPage.startPage();

        entries = addAccountingPage.accountSpinner().entries();
        assertThat(entries).hasSize(3);
        assertThat(entries.get(0).getText()).isEqualTo("Konto");
        assertThat(entries.get(1).getText()).isEqualTo("Sparkonto");
        assertThat(entries.get(2).getText()).isEqualTo("Bar");
        assertThat(addAccountingPage.accountSpinner().selectedEntry().getText()).isEqualTo("Konto");

        entries = addAccountingPage.typeSpinner().entries();
        assertThat(entries).hasSize(3);
        assertThat(entries.get(0).getText()).isEqualTo("Ausgabe");
        assertThat(entries.get(1).getText()).isEqualTo("Einnahme");
        assertThat(entries.get(2).getText()).isEqualTo("Übertrag");
        assertThat(addAccountingPage.typeSpinner().selectedEntry().getText()).isEqualTo("Ausgabe");

        entries = addAccountingPage.intervalSpinner().entries();
        assertThat(entries).hasSize(4);
        assertThat(entries.get(0).getText()).isEqualTo("Einmahlig");
        assertThat(entries.get(1).getText()).isEqualTo("Wöchentlich");
        assertThat(entries.get(2).getText()).isEqualTo("Monatlich");
        assertThat(entries.get(3).getText()).isEqualTo("Alle 3 Monate");
        assertThat(addAccountingPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Einmahlig");

        entries = addAccountingPage.categorySpinner().entries();
        assertThat(entries).hasSize(4);
        assertThat(entries.get(0).getText()).isEqualTo("Beruf");
        assertThat(entries.get(1).getText()).isEqualTo("Essen");
        assertThat(entries.get(2).getText()).isEqualTo("Freizeit");
        assertThat(entries.get(3).getText()).isEqualTo("Miete");
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

    private String year() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    private String year(int value) {
        return String.valueOf(value);
    }

    private String month() {
        int value = calendar.get(Calendar.MONTH) + 1;
        return withLeadingZero(value);
    }

    private String month(int value) {
        return withLeadingZero(value);
    }

    private String day() {
        int value = calendar.get(Calendar.DAY_OF_MONTH);
        return withLeadingZero(value);
    }

    private String day(int value) {
        return withLeadingZero(value);
    }

    private String withLeadingZero(int value) {
        String asString = String.valueOf(value);
        if (value >= 10) {
            return asString;
        }
        return "0" + asString;
    }
}
