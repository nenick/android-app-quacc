package de.nenick.quacc.addaccounting;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.nenick.robolectricpages.dialogs.RoboDatePickerDialog;
import de.nenick.robolectric.pages.RoboAddAccountingProcessPage;
import de.nenick.robolectric.UnitTesting;
import de.nenick.robolectricpages.RoboBaseTest;
import de.nenick.robolectricpages.components.RoboSpinnerEntry;

import static org.assertj.core.api.Assertions.assertThat;

public class AddAccountingProcessSpec extends UnitTesting {

    RoboBaseTest<AddAccountActivity_> roboBaseTest = new RoboBaseTest<>();
    RoboAddAccountingProcessPage addAccountingProcessPage = new RoboAddAccountingProcessPage(roboBaseTest);
    RoboDatePickerDialog datePickerDialog = new RoboDatePickerDialog();

    List<RoboSpinnerEntry> entries;
    Calendar calendar = Calendar.getInstance(Locale.GERMAN);

    @Test
    public void shouldHaveCorrectInitialState() {
        addAccountingProcessPage.startRoboPage();

        entries = addAccountingProcessPage.typeSpinner().entries();
        assertThat(entries).hasSize(2);
        assertThat(entries.get(0).getText()).isEqualTo("Ausgabe");
        assertThat(entries.get(1).getText()).isEqualTo("Einnahme");
        assertThat(addAccountingProcessPage.typeSpinner().selectedEntry().getText()).isEqualTo("Ausgabe");

        entries = addAccountingProcessPage.intervalSpinner().entries();
        assertThat(entries).hasSize(4);
        assertThat(entries.get(0).getText()).isEqualTo("Einmahlig");
        assertThat(entries.get(1).getText()).isEqualTo("Wöchentlich");
        assertThat(entries.get(2).getText()).isEqualTo("Monatlich");
        assertThat(entries.get(3).getText()).isEqualTo("Alle 3 Monate");
        assertThat(addAccountingProcessPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Einmahlig");

        entries = addAccountingProcessPage.categorySpinner().entries();
        assertThat(entries).hasSize(4);
        assertThat(entries.get(0).getText()).isEqualTo("Beruf");
        assertThat(entries.get(1).getText()).isEqualTo("Essen");
        assertThat(entries.get(2).getText()).isEqualTo("Freizeit");
        assertThat(entries.get(3).getText()).isEqualTo("Miete");
        assertThat(addAccountingProcessPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Einmahlig");

        assertThat(addAccountingProcessPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(), month(), year()));
    }

    @Test
    public void shouldShowValueFromDatePicker() {
        addAccountingProcessPage.startRoboPage();
        addAccountingProcessPage.dateField().click();
        assertThat(datePickerDialog.isShowing()).isTrue();
        datePickerDialog.pickDate(21, 12, 2012);
        datePickerDialog.clickOk();
        assertThat(addAccountingProcessPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(21), month(12), year(2012)));
    }

    @Test
    public void shouldSaveStateForConfigChanges() {
        addAccountingProcessPage.startRoboPage();

        addAccountingProcessPage.typeSpinner().entries().get(1).select();
        addAccountingProcessPage.intervalSpinner().entries().get(2).select();
        addAccountingProcessPage.categorySpinner().entries().get(3).select();

        addAccountingProcessPage.dateField().click();
        datePickerDialog.pickDate(21, 12, 2012);
        datePickerDialog.clickOk();

        roboBaseTest.activityController.restart();

        assertThat(addAccountingProcessPage.typeSpinner().selectedEntry().getText()).isEqualTo("Einnahme");
        assertThat(addAccountingProcessPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Monatlich");
        assertThat(addAccountingProcessPage.categorySpinner().selectedEntry().getText()).isEqualTo("Miete");
        assertThat(addAccountingProcessPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(21), month(12), year(2012)));
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
        if(value >= 10) {
            return asString;
        }
        return "0" + asString;
    }
}
