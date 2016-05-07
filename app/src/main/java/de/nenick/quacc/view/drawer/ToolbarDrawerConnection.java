package de.nenick.quacc.view.drawer;

import android.support.annotation.StringRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;

@EBean
public class ToolbarDrawerConnection {

    @RootContext
    AppCompatActivity activity;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.activity_booking_entries)
    DrawerLayout drawer;

    public void setTitle(CharSequence title) {
        toolbar.setTitle(title);
    }

    public void setTitle(@StringRes int resId) {
        toolbar.setTitle(resId);
    }

    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @AfterViews
    void registerActionBarDrawerToggle() {
        activity.setSupportActionBar(toolbar);

        // set up the hamburger icon to open and close the drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
}
