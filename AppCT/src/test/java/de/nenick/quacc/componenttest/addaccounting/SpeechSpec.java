package de.nenick.quacc.componenttest.addaccounting;


import android.speech.SpeechRecognizer;

import org.junit.Test;

import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.AddAccountingFragment;
import de.nenick.quacc.addaccounting.RoboAddAccountingPage;
import de.nenick.quacc.componenttest.RoboComponentTest;
import de.nenick.quacc.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SpeechSpec extends RoboComponentTest {

    RoboSup<AddAccountingActivity_, AddAccountingFragment> robo = new RoboSup<>();
    RoboAddAccountingPage addAccountingPage = new RoboAddAccountingPage(robo);

    @Test
    public void shouldShowMatches() {
        addAccountingPage.startPageWithSpeechMock(mock(SpeechRecognizer.class));
        addAccountingPage.speechResult("1 2 3", "one two three");
        assertThat(addAccountingPage.speechResultField().getText()).isEqualTo("[1 2 3] [one two three] ");
    }

}
