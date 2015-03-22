package de.nenick.quacc.addaccounting;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import de.nenick.quacc.R;
import de.nenick.quacc.speechrecognition.SpeechListener;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RobolectricSupportedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class AddAccountingFragmentSpeechTest extends RobolectricSupportedTest {

    RoboSup<AddAccountingActivity_, AddAccountingFragment> robo = new RoboSup<>();
    RoboAddAccountingPage addAccountingPage = new RoboAddAccountingPage(robo);

    @Mock
    SpeechRecognizer mockSpeechRecognizer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RoboAddAccountingUcDefaultResults.apply();
    }

    @Test
    public void shouldStartSpeechRecognitionOnClick() {
        addAccountingPage.startPageWithSpeechMock(mockSpeechRecognizer);
        thenSpeechButtonShowMicIsOff();
        addAccountingPage.speechButton().click();
        thenSpeechButtonShowMicIsOn();
    }

    @Test
    public void shouldStopSpeechRecognitionOnClickAgain() {
        givenStartedListening();
        addAccountingPage.speechButton().click();
        verify(mockSpeechRecognizer).stopListening();
        thenSpeechButtonShowMicIsOff();
    }

    @Test
    public void shouldShowSingleMatch() {
        givenStartedListening();
        addAccountingPage.speechResult("tell me something");
        assertThat(addAccountingPage.speechResultField().getText()).isEqualTo("[tell me something] ");
    }

    @Test
    public void shouldShowMultipleMatches() {
        givenStartedListening();
        addAccountingPage.speechResult("1 2 3", "one two three");
        assertThat(addAccountingPage.speechResultField().getText()).isEqualTo("[1 2 3] [one two three] ");
    }

    @Test
    public void shouldStopSpeechRecognition() {
        givenStartedListening();
        robo.activityController.pause();
        verify(mockSpeechRecognizer).stopListening();
    }

    @Test
    public void shouldDestroySpeechRecognition() {
        givenStartedListening();
        robo.activityController.destroy();
        verify(mockSpeechRecognizer).destroy();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldFailWithoutMatches() {
        givenStartedListening();
        addAccountingPage.speechResult();
    }

    @Test
    public void shouldShowMicIsOffOnError() {
        givenStartedListening();
        addAccountingPage.speechError(0);
        thenSpeechButtonShowMicIsOff();
    }

    @Test
    public void shouldShowMicIsOffOnResult() {
        givenStartedListening();
        addAccountingPage.speechResult("tell me something");
        thenSpeechButtonShowMicIsOff();
    }

    private void givenStartedListening() {
        addAccountingPage.startPageWithSpeechMock(mockSpeechRecognizer);
        addAccountingPage.speechButton().click();
        verify(mockSpeechRecognizer).startListening(any(Intent.class));
    }

    public Bundle speechResultBundle(String text) {
        ArrayList<String> speechResultText = new ArrayList<>();
        speechResultText.add(text);
        Bundle speechResultBundle = new Bundle();
        speechResultBundle.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, speechResultText);
        return speechResultBundle;
    }

    private void thenSpeechButtonShowMicIsOff() {
        assertThat(addAccountingPage.speechButton().getDrawableResId()).isEqualTo(R.drawable.ic_action_mic);
    }

    private void thenSpeechButtonShowMicIsOn() {
        assertThat(addAccountingPage.speechButton().getDrawableResId()).isEqualTo(R.drawable.ic_action_micoff);
    }
}
