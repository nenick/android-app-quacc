package de.nenick.quacc.componenttest.addaccounting;


import org.junit.Test;

import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.AddAccountingFragment;
import de.nenick.quacc.addaccounting.RoboAddAccountingPage;
import de.nenick.quacc.componenttest.BaseCT;
import de.nenick.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class SpeechSpec extends BaseCT {

    RoboSup<AddAccountingActivity_, AddAccountingFragment> robo = new RoboSup<>();
    RoboAddAccountingPage addAccountingPage = new RoboAddAccountingPage(robo);

    @Test
    public void shouldShowMultipleMatches() {
        addAccountingPage.startPage();
        addAccountingPage.speechResult("1 2 3", "one two three");
        assertThat(addAccountingPage.speechResultField().getText()).isEqualTo("[1 2 3] [one two three] ");
    }

}
