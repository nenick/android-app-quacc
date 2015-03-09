package de.nenick.robolectric.pages;

import de.nenick.quacc.addaccounting.AddAccountActivity_;
import de.nenick.quacc.R;
import de.nenick.robolectricpages.components.RoboTextView;
import de.nenick.robolectricpages.RoboBasePage;
import de.nenick.robolectricpages.RoboBaseTest;
import de.nenick.robolectricpages.components.RoboSpinner;

public class RoboAddAccountingProcessPage extends RoboBasePage<AddAccountActivity_> {

    public RoboAddAccountingProcessPage(RoboBaseTest<AddAccountActivity_> roboBaseTest) {
        super(AddAccountActivity_.class, roboBaseTest);
    }

    public RoboSpinner typeSpinner() {
        return new RoboSpinner(roboBaseTest, R.id.accountingType);
    }

    public RoboTextView dateField() {
        return new RoboTextView(roboBaseTest, R.id.date);
    }

    public RoboSpinner intervalSpinner() {
        return new RoboSpinner(roboBaseTest, R.id.interval);
    }

    public RoboSpinner categorySpinner() {
        return new RoboSpinner(roboBaseTest, R.id.category);
    }
}
