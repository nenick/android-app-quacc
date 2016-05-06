package de.nenick.quacc.view.accounting_overview;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

import org.robolectric.Robolectric;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.util.Scheduler;

import de.nenick.quacc.R;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.quacc.robolectric.RoboSupActionbarMenuItem;
import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.components.RoboBaseComponent;
import de.nenick.robolectricpages.components.RoboListView;

public class RoboAccountingListDrawer extends RoboBaseComponent {
    public RoboAccountingListDrawer(Robo robo) {
        super(robo);
    }

    public RoboListView accountList() {
        return new RoboListView(robo, R.id.accountList);
    }

    public void updateOpenCloseState() {
        // https://github.com/robolectric/robolectric/pull/1233
        Robolectric.getForegroundThreadScheduler().advanceToLastPostedRunnable();
        Robolectric.getForegroundThreadScheduler().advanceToLastPostedRunnable();
        Robolectric.getForegroundThreadScheduler().advanceToLastPostedRunnable();

        robo.activity.findViewById(R.id.drawer_layout).computeScroll();
    }

    public void closePerHomePress() {
        new RoboSupActionbarMenuItem((RoboSup)robo, android.R.id.home, R.id.navigation_drawer).click();
        updateOpenCloseState();
    }

    public void open() {
        new RoboSupActionbarMenuItem((RoboSup)robo, android.R.id.home, R.id.navigation_drawer).click();
        updateOpenCloseState();
    }

    public void close() {
        new RoboSupActionbarMenuItem((RoboSup)robo, android.R.id.home, R.id.navigation_drawer).click();
        updateOpenCloseState();
    }
}
