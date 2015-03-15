package de.nenick.quacc.addaccounting;

import de.nenick.quacc.R;
import de.nenick.quacc.addaccounting.AddAccountingActivity;
import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.addaccounting.AddAccountingFragment_;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectric.RoboSupTest;
import de.nenick.robolectricpages.components.RoboButton;
import de.nenick.robolectricpages.components.RoboImageButton;
import de.nenick.robolectricpages.components.RoboSpinner;
import de.nenick.robolectricpages.components.RoboTextView;

public class RoboAddAccountingPage extends RoboSupPage<AddAccountingActivity_, AddAccountingFragment> {

    public RoboAddAccountingPage(RoboSupTest<AddAccountingActivity_, AddAccountingFragment> robo) {
        super(AddAccountingActivity_.class, robo, AddAccountingActivity.TAG_FRAGMENT);
    }

    public void startPageWithMocks(SpeechRecognitionWrapper mockSpeechRecognition) {
        createPage();
        robo.fragment.speechRecognition = mockSpeechRecognition;
        startCreatedPage();
    }

    public RoboSpinner typeSpinner() {
        return new RoboSpinner(robo, R.id.accountingType);
    }

    public RoboTextView dateField() {
        return new RoboTextView(robo, R.id.date);
    }

    public RoboSpinner intervalSpinner() {
        return new RoboSpinner(robo, R.id.interval);
    }

    public RoboSpinner categorySpinner() {
        return new RoboSpinner(robo, R.id.category);
    }

    public RoboSpinner accountSpinner() {
        return new RoboSpinner(robo, R.id.account);
    }

    public RoboTextView speechResultField() {
        return new RoboTextView(robo, R.id.speechResult);
    }

    public RoboImageButton speechButton() {
        return new RoboImageButton(robo, R.id.speechRecognitionBtn);
    }
}
