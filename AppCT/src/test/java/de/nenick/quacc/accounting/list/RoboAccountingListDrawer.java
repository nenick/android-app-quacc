package de.nenick.quacc.accounting.list;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

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
        View drawer = robo.activity.findViewById(R.id.navigation_drawer);
        DrawerLayout view = (DrawerLayout) robo.activity.findViewById(R.id.drawer_layout);
        view.openDrawer(drawer);
        updateOpenCloseState();
    }

    public void close() {
        View drawer = robo.activity.findViewById(R.id.navigation_drawer);
        DrawerLayout view = (DrawerLayout) robo.activity.findViewById(R.id.drawer_layout);
        view.closeDrawer(drawer);
        updateOpenCloseState();
        // with robolectric 3.0-rc2 there is a bug where we must call open to toggle the drawer state
        open();
    }
}
