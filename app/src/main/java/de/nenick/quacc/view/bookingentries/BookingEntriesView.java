package de.nenick.quacc.view.bookingentries;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.toolscollection.lazyadapter.LazyAdapter;

@EBean
class BookingEntriesView {

    public interface ViewCallback {
        void onClickMonthUp();

        void onClickMonthDown();

        void onClickYearUp();

        void onClickYearDown();
    }

    @EViewGroup(R.layout.fragment_booking_entries)
    public static class Layout extends FrameLayout {
        public Layout(Context context) {
            super(context);
        }
    }

    @RootContext
    protected Context context;

    @ViewById(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @ViewById(R.id.bookingDateSelectorLayout)
    protected View dateSelector;

    @ViewById(R.id.month)
    protected TextView selectedMonth;

    @ViewById(R.id.year)
    protected TextView selectedYear;

    protected ViewCallback viewCallback;

    public Layout root() {
        return BookingEntriesView_.Layout_.build(context);
    }

    public void setViewCallback(ViewCallback viewCallback) {
        this.viewCallback = viewCallback;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        LazyAdapter.inject(recyclerView, adapter);
    }

    public void setMonth(String month) {
        selectedMonth.setText(month);
    }

    public void setYear(String year) {
        selectedYear.setText(year);
    }

    public void toggleDateSelector() {
        dateSelector.setVisibility(dateSelector.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    @Click(R.id.yearDown)
    protected void onClickYearDown() {
        viewCallback.onClickYearDown();
    }

    @Click(R.id.yearUp)
    protected void onClickYearUp() {
        viewCallback.onClickYearUp();
    }

    @Click(R.id.monthDown)
    protected void onClickMonthDown() {
        viewCallback.onClickMonthDown();
    }

    @Click(R.id.monthUp)
    protected void onClickMonthUp() {
        viewCallback.onClickMonthUp();
    }
}
