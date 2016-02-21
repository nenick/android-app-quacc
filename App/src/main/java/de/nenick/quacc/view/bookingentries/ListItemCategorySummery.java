package de.nenick.quacc.view.bookingentries;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
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
import de.nenick.quacc.expandablerecyclerview.ExpandableItemIndicator;

/**
 * Group for expandable list view.
 */
@EBean
class ListItemCategorySummery extends AbstractExpandableItemViewHolder {

    @EViewGroup(R.layout.item_accounting_group)
    static class ListItemView extends RelativeLayout {
        public ListItemView(Context context) {
            super(context);
        }
    }

    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.category)
    TextView category;

    @ViewById(R.id.amount)
    TextView amount;

    @ViewById(R.id.endDate)
    TextView endDate;

    @ViewById(R.id.dateSeparator)
    TextView dateSeparator;

    @ViewById(R.id.indicator)
    ExpandableItemIndicator expandableItemIndicator;

    public ListItemCategorySummery(Context context) {
        super(createView(context));
        injectViewComponents();
    }

    public void bind(BookingEntryCursor item) {
        date.setText(item.getDateOrNull("minDate").toString());
        category.setText(item.getCategoryName());
        amount.setText(String.valueOf(item.getAmount()));
        endDate.setText(item.getDate().toString());

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

        enableExpansionSupport();
    }

    private void enableExpansionSupport() {
        // mark as clickable
        itemView.setClickable(true);

        // set background resource (target view ID: container)
        final int expandState = getExpandStateFlags();

        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {
            int bgResId;
            boolean isExpanded;
            boolean animateIndicator = ((expandState & ExpandableItemConstants.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);

            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0) {
                //bgResId = R.drawable.bg_group_item_expanded_state;
                isExpanded = true;
            } else {
                //bgResId = R.drawable.bg_group_item_normal_state;
                isExpanded = false;
            }

            //holder.mContainer.setBackgroundResource(bgResId);
            expandableItemIndicator.setExpandedState(isExpanded, animateIndicator);
        }
    }

    private static ListItemView createView(Context context) {
        return ListItemCategorySummery_.ListItemView_.build(context);
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
        dateSeparator.setTextColor(itemView.getResources().getColor(R.color.positiveTextSmall));
        endDate.setTextColor(itemView.getResources().getColor(R.color.positiveTextSmall));
        category.setTextColor(itemView.getResources().getColor(R.color.positiveText));
        amount.setTextColor(itemView.getResources().getColor(R.color.positiveText));
    }

    private void showAsOutgoing() {
        itemView.setBackgroundColor(itemView.getResources().getColor(R.color.negativeBackground));
        date.setTextColor(itemView.getResources().getColor(R.color.negativeTextSmall));
        dateSeparator.setTextColor(itemView.getResources().getColor(R.color.negativeTextSmall));
        endDate.setTextColor(itemView.getResources().getColor(R.color.negativeTextSmall));
        category.setTextColor(itemView.getResources().getColor(R.color.negativeText));
        amount.setTextColor(itemView.getResources().getColor(R.color.negativeText));
    }

    private void showAsTransfer() {
        itemView.setBackgroundColor(itemView.getResources().getColor(R.color.neutralBackground));
        date.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
        dateSeparator.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
        endDate.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
        category.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
        amount.setTextColor(itemView.getResources().getColor(R.color.neutralBackground));
    }
}
