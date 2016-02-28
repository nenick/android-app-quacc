package de.nenick.quacc.view.bookingentries;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;

import de.nenick.expandablerecyclerview.ExpandableCursorTreeAdapter;
import de.nenick.quacc.R;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;

/**
 * Child for expandable list view group.
 */
@EBean
class BookingEntryListItem extends ExpandableCursorTreeAdapter.ListItemHolder<BookingEntryCursor> {

    @EViewGroup(R.layout.item_accounting_child)
    static class ListItemView extends RelativeLayout {
        ListItemView(Context context) {
            super(context);
        }

        static ListItemView create(Context context) {
            return BookingEntryListItem_.ListItemView_.build(context);
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

    public static BookingEntryListItem create(Context context) {
        return BookingEntryListItem_.getInstance_(context);
    }

    public BookingEntryListItem(Context context) {
        super(ListItemView.create(context));
        injectViewComponents();
    }

    @Override
    public void onBind(BookingEntryCursor item) {
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

    private void injectViewComponents() {
        ((OnViewChangedListener) this).onViewChanged(new HasViews() {
            @Override
            public View findViewById(int id) {
                return itemView.findViewById(id);
            }
        });
    }

    private void showAsIncome() {
        tintFields(itemView.getResources().getColor(R.color.positiveBackground), itemView.getResources().getColor(R.color.positiveTextSmall), itemView.getResources().getColor(R.color.positiveText));
    }

    private void showAsOutgoing() {
        tintFields(itemView.getResources().getColor(R.color.negativeBackground), itemView.getResources().getColor(R.color.negativeTextSmall), itemView.getResources().getColor(R.color.negativeText));
    }

    private void showAsTransfer() {
        tintFields(itemView.getResources().getColor(R.color.neutralBackground), itemView.getResources().getColor(R.color.neutralBackground), itemView.getResources().getColor(R.color.neutralBackground));
    }

    private void tintFields(int color, int color2, int color3) {
        itemView.setBackgroundColor(color);
        date.setTextColor(color2);
        interval.setTextColor(color3);
        category.setTextColor(color3);
        comment.setTextColor(color2);
        amount.setTextColor(color3);
    }
}
