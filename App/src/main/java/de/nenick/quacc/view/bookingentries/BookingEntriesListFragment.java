package de.nenick.quacc.view.bookingentries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.joda.time.DateTime;

import de.nenick.quacc.view.accounting_overview.adapter.AccountingTreeAdapter_;

@EFragment
public class BookingEntriesListFragment extends Fragment {

    BookingEntriesListView view;

    @FragmentArg
    String accountName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = BookingEntriesListView_.build(inflater.getContext(), null);
    }

    @AfterViews
    void onAfterViewsCreated() {
        BookingSummeryAndEntriesTreeCursorAdapter dataSource = BookingSummeryAndEntriesTreeCursorAdapter_.getInstance_(getContext());
        dataSource.setAccount(accountName);
        dataSource.changeFor(new DateTime(0), new DateTime());
        view.recyclerView.setAdapter(new BookingEntriesListAdapter(dataSource));
    }
}
