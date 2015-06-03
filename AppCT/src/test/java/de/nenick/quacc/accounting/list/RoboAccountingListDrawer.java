package de.nenick.quacc.accounting.list;

import android.support.v4.widget.DrawerLayout;

import org.robolectric.Robolectric;

import de.nenick.quacc.R;
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
        Robolectric.getForegroundThreadScheduler().advanceToLastPostedRunnable();
        robo.activity.findViewById(R.id.drawer_layout).computeScroll();
    }

    public void open() {
        ((DrawerLayout)robo.activity.findViewById(R.id.drawer_layout)).openDrawer(robo.activity.findViewById(R.id.navigation_drawer));
        updateOpenCloseState();
    }

    public void close() {
        ((DrawerLayout)robo.activity.findViewById(R.id.drawer_layout)).closeDrawer(robo.activity.findViewById(R.id.navigation_drawer));
        updateOpenCloseState();
    }
}
