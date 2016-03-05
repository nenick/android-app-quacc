package de.nenick.quacc.view.accounting_edit;

import de.nenick.quacc.R;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupActionbarMenuItem;
import de.nenick.robolectricpages.components.RoboActionbar;
import de.nenick.robolectricpages.components.RoboActionbarMenuItem;

public class RoboEditAccountingActionbar extends RoboActionbar {

    private RoboSup roboSup;

    public RoboEditAccountingActionbar(RoboSup roboSup) {
        super(roboSup);
        this.roboSup = roboSup;
    }

    public RoboActionbarMenuItem confirmMenuItem() {
        return new RoboSupActionbarMenuItem(roboSup, R.id.confirm, EditAccountingActivity.TAG_FRAGMENT);
    }
}
