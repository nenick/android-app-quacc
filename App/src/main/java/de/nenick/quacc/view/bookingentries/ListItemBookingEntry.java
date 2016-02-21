package de.nenick.quacc.view.bookingentries;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;

import de.nenick.quacc.R;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.expandablerecyclerview.AbstractSimpleDataProvider;

/**
 * Child for expandable list view group.
 */
@EBean
class ListItemBookingEntry extends AbstractExpandableItemViewHolder {

    @EViewGroup(R.layout.item_accounting_child)
    static class ListItemView extends RelativeLayout {
        public ListItemView(Context context) {
            super(context);
        }
    }

    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.interval)
    TextView interval;

    @ViewById(R.id.category)
    TextView category;

    @ViewById(R.id.comment)
    TextView comment;

    @ViewById(R.id.amount)
    TextView amount;

    public ListItemBookingEntry(Context context) {
        super(createView(context));
        injectViewComponents();
    }

    public void bind(BookingEntryCursor item) {
        date.setText(item.getDate().toString());
        interval.setText(item.getInterval());
        category.setText(item.getCategoryName());
        comment.setText(item.getComment());
        amount.setText(String.valueOf(item.getAmount()));

        switch (BookingDirectionOption.valueOf(item.getDirection())) {
            case incoming:
                showAsIncome();
                break;
            case outgoing:
                showAsOutgoing();
                break;
            case transfer:
                showAsTransfer();
                break;
            default:
                throw new IllegalStateException("Not supported type: " + BookingDirectionOption.valueOf(item.getDirection()));
        }
    }

    private static ListItemView createView(Context context) {
        return ListItemBookingEntry_.ListItemView_.build(context);
    }

    private void injectViewComponents() {
        ((OnViewChangedListener)this).onViewChanged(new HasViews() {
            @Override
            public View findViewById(int id) {
                return itemView.findViewById(id);
            }
        });
    }

    private void showAsIncome() {
        itemView.setBackgroundColor(itemView.getResources().getColor(R.color.positiveBackground));
        date.setTextColor(itemView.getResources().getColor(R.color.positiveTextSmall));
        interval.setTextColor(itemView.getResources().getColor(R.color.positiveText));
        category.setTextColor(itemView.getResources().getColor(R.color.positiveText));
        comment.setTextColor(itemView.getResources().getColor(R.color.positiveTextSmall));
        amount.setTextColor(itemView.getResources().getColor(R.color.positiveText));
    }

    private void showAsOutgoing() {
        itemView.setBackgroundColor(itemView.getResources().getColor(R.color.negativeBackground));
        date.setTextColor(itemView.getResources().getColor(R.color.negativeTextSmall));
        interval.setTextColor(itemView.getResources().getColor(R.color.negativeText));
        category.setTextColor(itemView.getResources().getColor(R.color.negativeText));
        comment.setTextColor(itemView.getResources().getColor(R.color.negativeTextSmall));
        amount.setTextColor(itemView.getResources().getColor(R.color.negativeText));
    }

    private void showAsTransfer() {
        itemView.setBackgroundColor(itemView.getResources().getColor(R.color.neutralBackground));
        date.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
        interval.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
        category.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
        comment.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
        amount.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
    }
}
