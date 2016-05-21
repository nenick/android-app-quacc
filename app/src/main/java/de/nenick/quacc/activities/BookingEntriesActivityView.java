package de.nenick.quacc.activities;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.view.bookingentries.BookingEntriesFragment;
import de.nenick.quacc.view.drawer.AccountNavigationDrawer;

@EBean
public class BookingEntriesActivityView {

    public interface ViewCallback {
        void onSelectAccount(long accountId);
        void onClickNewBookingEntry();
    }

    @EViewGroup(R.layout.activity_booking_entries)
    public static class Layout extends FrameLayout {
        public Layout(Context context) {
            super(context);
        }
    }

    @RootContext
    protected AppCompatActivity context;

    @FragmentById(R.id.fragment_booking_entries)
    protected BookingEntriesFragment bookingEntries;

    @ViewById(R.id.navigation_drawer)
    protected AccountNavigationDrawer accountNavigationDrawer;

    @ViewById(R.id.activity_booking_entries)
    protected DrawerLayout drawer;

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    private ViewCallback callback;

    public Layout root() {
        return BookingEntriesActivityView_.Layout_.build(context);
    }

    @AfterViews
    protected void onAfterViewsCreated() {
        context.setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(context, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        accountNavigationDrawer.setAccountSelectionListener(new AccountNavigationDrawer.AccountSelectionListener() {
            @Override
            public void onAccountSelection(long accountId) {
                drawer.closeDrawer(GravityCompat.START);
                callback.onSelectAccount(accountId);
            }
        });
    }

    @Click(R.id.btn_add_booking)
    protected void onClickNewBookingEntry() {
        callback.onClickNewBookingEntry();
    }

    public void setCallback(ViewCallback callback) {
        this.callback = callback;
    }

    public void showAccountContent(long accountId) {
        bookingEntries.setAccount(accountId);
        accountNavigationDrawer.selectAccount(accountId);
    }

    public void showAccounts(AccountCursor cursor) {
        accountNavigationDrawer.bindAccounts(cursor);
    }
}
