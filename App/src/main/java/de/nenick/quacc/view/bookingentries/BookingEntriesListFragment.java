package de.nenick.quacc.view.bookingentries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.joda.time.DateTime;

import de.nenick.toolscollection.LazyAdapter;

@EFragment
public class BookingEntriesListFragment extends Fragment {

    BookingEntriesListView view;

    @Bean
    BookingEntriesListAdapter adapter;

    @FragmentArg
    String accountName;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = BookingEntriesListView_.build(inflater.getContext(), null);
    }

    @AfterViews
    void onAfterViewsCreated() {
        initBookingEntriesList();
    }

    private void initBookingEntriesList() {
        LazyAdapter.inject(this.view.recyclerView, adapter);
        adapter.update(accountName, new DateTime(0), new DateTime());
    }
}
