package de.nenick.quacc.accounting.create;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.CreateAccountingActivity;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.quacc.robolectric.RoboSupActionbarMenuItem;
import de.nenick.robolectricpages.components.RoboActionbar;
import de.nenick.robolectricpages.components.RoboActionbarMenuItem;

public class RoboCreateAccountingActionbar extends RoboActionbar {

    private RoboSup roboSup;

    public RoboCreateAccountingActionbar(RoboSup roboSup) {
        super(roboSup);
        this.roboSup = roboSup;
    }

    public RoboActionbarMenuItem cofirmMenuItem() {
        return new RoboSupActionbarMenuItem(roboSup, R.id.confirm, CreateAccountingActivity.TAG_FRAGMENT);
    }
}
