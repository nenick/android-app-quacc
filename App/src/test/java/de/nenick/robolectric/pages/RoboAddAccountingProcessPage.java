package de.nenick.robolectric.pages;

import de.nenick.quacc.addaccounting.AddAccountingActivity_;
import de.nenick.quacc.R;
import de.nenick.robolectricpages.components.RoboTextView;
import de.nenick.robolectricpages.RoboBasePage;
import de.nenick.robolectricpages.RoboBaseTest;
import de.nenick.robolectricpages.components.RoboSpinner;

public class RoboAddAccountingProcessPage extends RoboBasePage<AddAccountingActivity_> {

    public RoboAddAccountingProcessPage(RoboBaseTest<AddAccountingActivity_> roboBaseTest) {
        super(AddAccountingActivity_.class, roboBaseTest);
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
