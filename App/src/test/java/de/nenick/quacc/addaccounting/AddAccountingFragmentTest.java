package de.nenick.quacc.addaccounting;

import android.graphics.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import de.nenick.quacc.TestQuAccApplication;
import de.nenick.quacc.core.accounting.ParseAccountingValueUc;
import de.nenick.quacc.datepicker.DatePickerFormatUtil;
import de.nenick.quacc.robolectric.RoboAppTest;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.quacc.speechrecognition.SpeechListener;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;
import de.nenick.robolectricpages.components.RoboSpinnerEntry;

import static de.nenick.quacc.TestDateUtil.day;
import static de.nenick.quacc.TestDateUtil.month;
import static de.nenick.quacc.TestDateUtil.year;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class AddAccountingFragmentTest extends RoboAppTest {

    RoboSup<AddAccountingActivity_, AddAccountingFragment> robo = new RoboSup<>();
    RoboAddAccountingPage addAccountingPage = new RoboAddAccountingPage(robo);

    List<RoboSpinnerEntry> entries;

    @Mock
    SpeechRecognitionWrapper mockSpeechRecognition;

    @Captor
    ArgumentCaptor<SpeechListener> speechRecognitionListenerArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RoboAddAccountingUcDefaultResults.apply();
    }

    @After
    public void finish() {
        if(robo.activityController != null) {
            robo.activityController.destroy();
        }
    }

    @Test
    public void shouldShowInitialValues() {
        addAccountingPage.startPage();

        entries = addAccountingPage.accountSpinner().entries();
        assertThat(entries).hasSize(3);

        entries = addAccountingPage.typeSpinner().entries();
        assertThat(entries).hasSize(2);

        entries = addAccountingPage.intervalSpinner().entries();
        assertThat(entries).hasSize(4);

        entries = addAccountingPage.categorySpinner().entries();
        assertThat(entries).hasSize(4);

        assertThat(addAccountingPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(), month(), year()));
    }

    @Test
    public void shouldShowValueFromDatePicker() {
        addAccountingPage.startPage();
        addAccountingPage.dateField().click();
        assertThat(addAccountingPage.dialog().datePicker().isShowing()).isTrue();
        addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);
        addAccountingPage.dialog().datePicker().clickOk();
        assertThat(addAccountingPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(21), month(12), year(2012)));
    }

    @Test
    public void shouldSaveStateForConfigChanges() {
        addAccountingPage.startPage();

        addAccountingPage.accountSpinner().entries().get(2).select();
        addAccountingPage.typeSpinner().entries().get(1).select();
        addAccountingPage.intervalSpinner().entries().get(2).select();
        addAccountingPage.categorySpinner().entries().get(3).select();
        addAccountingPage.dateField().click();
        addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);
        addAccountingPage.dialog().datePicker().clickOk();
        addAccountingPage.valueField().setText("60.00");

        robo.activityController.restart();

        assertThat(addAccountingPage.accountSpinner().selectedEntry().getText()).isEqualTo("Bar");
        assertThat(addAccountingPage.typeSpinner().selectedEntry().getText()).isEqualTo("Einnahme");
        assertThat(addAccountingPage.intervalSpinner().selectedEntry().getText()).isEqualTo("Monatlich");
        assertThat(addAccountingPage.categorySpinner().selectedEntry().getText()).isEqualTo("Miete");
        assertThat(addAccountingPage.dateField().getText()).isEqualTo(String.format("%s.%s.%s", day(21), month(12), year(2012)));
        assertThat(addAccountingPage.valueField().getText()).isEqualTo("60.00");
    }

    @Test
    public void shouldAddAccounting() throws ParseException {
        given(TestQuAccApplication.coreModuleMocks.parseAccountingValueUc.apply(anyString())).willReturn(new ParseAccountingValueUc.Result(ParseAccountingValueUc.ParseResult.Successful));
        addAccountingPage.startPage();

        addAccountingPage.accountSpinner().entries().get(2).select();
        addAccountingPage.typeSpinner().entries().get(1).select();
        addAccountingPage.intervalSpinner().entries().get(2).select();
        addAccountingPage.categorySpinner().entries().get(3).select();
        addAccountingPage.dateField().click();
        addAccountingPage.dialog().datePicker().pickDate(21, 12, 2012);
        addAccountingPage.dialog().datePicker().clickOk();
        addAccountingPage.valueField().setText("60.00");
        addAccountingPage.commentField().setText("money money");

        addAccountingPage.actionbar().cofirmMenuItem().click();

        DateFormat df = DatePickerFormatUtil.getDefaultDateFormat();
        verify(TestQuAccApplication.coreModuleMocks.addNewAccountingUc).apply("Bar", "Einnahme", "Monatlich", "Miete", df.parse("21.12.2012"), 0, "money money");
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailAtUnknownActivityResult() {
        AddAccountingFragment addAccountingFragment = new AddAccountingFragment();
        addAccountingFragment.onActivityResult(123456, 0, null);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailAtUnknownDateFormat() {
        given(TestQuAccApplication.coreModuleMocks.parseAccountingValueUc.apply(anyString())).willReturn(new ParseAccountingValueUc.Result(ParseAccountingValueUc.ParseResult.Successful));
        addAccountingPage.startPage();
        addAccountingPage.dateField().setText("2000-12-21");
        addAccountingPage.actionbar().cofirmMenuItem().click();
    }

    @Test
    public void shouldGiveFeedbackForInvalidAccountingValue() {
        addAccountingPage.startPage();
        assertThat(addAccountingPage.valueErrorField().getText()).isEmpty();
        given(TestQuAccApplication.coreModuleMocks.parseAccountingValueUc.apply(anyString())).willReturn(new ParseAccountingValueUc.Result(ParseAccountingValueUc.ParseResult.ZeroValue));
        addAccountingPage.actionbar().cofirmMenuItem().click();
        assertThat(addAccountingPage.valueErrorField().getText()).isNotEmpty();
    }
}
