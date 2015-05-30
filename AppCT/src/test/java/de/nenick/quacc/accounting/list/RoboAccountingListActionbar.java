package de.nenick.quacc.accounting.list;

import de.nenick.quacc.R;
import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.components.RoboActionbar;
import de.nenick.robolectricpages.components.RoboActionbarMenuItem;

public class RoboAccountingListActionbar extends RoboActionbar {

    public RoboAccountingListActionbar(Robo robo) {
        super(robo);
    }

    public RoboActionbarMenuItem categories() {
        return new RoboActionbarMenuItem(robo, R.id.category, null);
    }
}
