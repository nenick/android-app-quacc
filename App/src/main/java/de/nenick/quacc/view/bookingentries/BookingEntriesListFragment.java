package de.nenick.quacc.view.bookingentries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment
public class BookingEntriesListFragment extends Fragment {

    BookingEntriesListView view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = BookingEntriesListView_.build(inflater.getContext(), null);
    }

    @AfterViews
    void onAfterViewsCreated() {
        view.recyclerView.setAdapter(new BookingEntriesListAdapter(new ExampleSimpleDataProvider()));
    }
}
