package de.nenick.quacc.view.bookingentries;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;

@EViewGroup(R.layout.fragment_booking_entries_list)
class BookingEntriesListView extends FrameLayout {

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    public BookingEntriesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
