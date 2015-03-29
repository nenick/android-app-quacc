package de.nenick.quacc.addaccounting;

import de.nenick.quacc.R;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.quacc.robolectric.RoboSupActionbarMenuItem;
import de.nenick.robolectricpages.components.RoboActionbar;
import de.nenick.robolectricpages.components.RoboActionbarMenuItem;

public class RoboAddAccountingActionbar extends RoboActionbar {

    private RoboSup roboSup;

    public RoboAddAccountingActionbar(RoboSup roboSup) {
        super(roboSup);
        this.roboSup = roboSup;
    }

    public RoboActionbarMenuItem cofirmMenuItem() {
        return new RoboSupActionbarMenuItem(roboSup, R.id.confirm, AddAccountingActivity.TAG_FRAGMENT);
    }
}
