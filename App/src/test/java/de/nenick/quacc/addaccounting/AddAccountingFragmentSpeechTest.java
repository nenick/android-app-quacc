package de.nenick.quacc.addaccounting;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.nenick.quacc.TestQuAccApplication;
import de.nenick.quacc.speechrecognition.SpeechRecognitionListener;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RobolectricSupportedTest;
import de.nenick.robolectricpages.components.RoboSpinnerEntry;
import de.nenick.robolectricpages.dialogs.RoboDatePickerDialog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class AddAccountingFragmentSpeechTest extends RobolectricSupportedTest {

    RoboSup<AddAccountingActivity_, AddAccountingFragment> robo = new RoboSup<>();
    RoboAddAccountingPage addAccountingPage = new RoboAddAccountingPage(robo);

    @Mock
    SpeechRecognitionWrapper mockSpeechRecognition;

    @Captor
    ArgumentCaptor<SpeechRecognitionListener> speechRecognitionListenerArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RoboAddAccountingUcDefaultResults.apply();
    }

    @Test
    public void shouldToggleSpeechRecognition() {
        addAccountingPage.startPageWithMocks(mockSpeechRecognition);
        addAccountingPage.speechButton().click();
        verify(mockSpeechRecognition).toggle();
    }

    @Test
    public void shouldShowSingleMatch() {
        addAccountingPage.startPage();
        addAccountingPage.speechResult("tell me something");
        assertThat(addAccountingPage.speechResultField().getText()).isEqualTo("[tell me something] ");
    }

    @Test
    public void shouldShowMultipleMatches() {
        addAccountingPage.startPage();
        addAccountingPage.speechResult("1 2 3", "one two three");
        assertThat(addAccountingPage.speechResultField().getText()).isEqualTo("[1 2 3] [one two three] ");
    }

    @Test
    public void shouldDestroySpeechRecognition() {
        addAccountingPage.startPageWithMocks(mockSpeechRecognition);
        robo.activityController.destroy();
        verify(mockSpeechRecognition).destroy();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldFailWithoutMatches() {
        addAccountingPage.startPage();
        addAccountingPage.speechResult();
    }

    public Bundle speechResultBundle(String text) {
        ArrayList<String> speechResultText = new ArrayList<>();
        speechResultText.add(text);
        Bundle speechResultBundle = new Bundle();
        speechResultBundle.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, speechResultText);
        return speechResultBundle;
    }
}
