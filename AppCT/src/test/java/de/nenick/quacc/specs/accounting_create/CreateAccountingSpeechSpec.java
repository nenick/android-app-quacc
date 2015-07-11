package de.nenick.quacc.specs.accounting_create;


import android.speech.SpeechRecognizer;

import org.junit.Test;

import de.nenick.quacc.view.accounting_edit.RoboEditAccountingPage;
import de.nenick.robolectric.RoboComponentTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CreateAccountingSpeechSpec extends RoboComponentTestBase {

    RoboEditAccountingPage addAccountingPage = new RoboEditAccountingPage();

    @Test
    public void shouldShowMatches() {
        addAccountingPage.startPageWithSpeechMock(mock(SpeechRecognizer.class));
        addAccountingPage.speechResult("1 2 3");
        assertThat(addAccountingPage.speechResultField().getText()).isEqualTo("[1 2 3] ");
    }

    // RuntimeEnvironment.getRobolectricPackageManager().addResolveInfoForIntent(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), new ResolveInfo());

    /*
    @Test
    public void shouldStartSpeechRecognitionOnClick() {
        addAccountingPage.startPageWithSpeechMock(speechRecognizer);
        thenSpeechButtonShowMicIsOff();
        addAccountingPage.speechButton().click();
        thenSpeechButtonShowMicIsOn();
    }

    @Test
    public void shouldStopSpeechRecognitionOnClickAgain() {
        givenStartedListening();
        addAccountingPage.speechButton().click();
        verify(speechRecognizer).stopListening();
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
        verify(speechRecognizer).stopListening();
    }

    @Test
    public void shouldDestroySpeechRecognition() {
        givenStartedListening();
        robo.activityController.onViewFinish();
        verify(speechRecognizer).onViewFinish();
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
        addAccountingPage.startPageWithSpeechMock(speechRecognizer);
        addAccountingPage.speechButton().click();
        verify(speechRecognizer).startListening(any(Intent.class));
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
     */
}
