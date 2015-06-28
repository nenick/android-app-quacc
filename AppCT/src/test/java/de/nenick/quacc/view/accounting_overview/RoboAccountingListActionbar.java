package de.nenick.quacc.view.accounting_overview;

import android.support.v7.app.ActionBarActivity;

import de.nenick.quacc.R;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.components.RoboActionbar;
import de.nenick.robolectricpages.components.RoboActionbarMenuItem;

public class RoboAccountingListActionbar extends RoboActionbar {

    private RoboSup robo;

    public RoboAccountingListActionbar(RoboSup robo) {
        super(robo);
        this.robo = robo;
    }

    public RoboActionbarMenuItem categories() {
        return new RoboActionbarMenuItem(robo, R.id.category, null);
    }

    public RoboActionbarMenuItem accounts() {
        return new RoboActionbarMenuItem(robo, R.id.account, null);
    }

    public RoboActionbarMenuItem exportData() {
        return new RoboActionbarMenuItem(robo, R.id.export_data, null);
    }

    public RoboActionbarMenuItem importData() {
        return new RoboActionbarMenuItem(robo, R.id.import_data, null);
    }

    @Override
    public String title() {
        CharSequence title = ((ActionBarActivity) robo.activity).getSupportActionBar().getTitle();
        if(title != null) {
            return title.toString();
        } else {
            return null;
        }
    }
}
