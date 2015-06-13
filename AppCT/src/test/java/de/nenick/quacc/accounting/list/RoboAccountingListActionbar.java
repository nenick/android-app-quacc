package de.nenick.quacc.accounting.list;

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

    @Override
    public String title() {
        return ((ActionBarActivity)robo.activity).getSupportActionBar().getTitle().toString();
    }
}