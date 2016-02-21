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

    public void bind(AbstractSimpleDataProvider.ChildData item) {
        category.setText(item.getText());
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
}
