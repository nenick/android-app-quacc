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

    @ViewById(R.id.indicator)
    ExpandableItemIndicator expandableItemIndicator;

    public ListItemCategorySummery(Context context) {
        super(createView(context));
        injectViewComponents();
    }

    public void bind(AbstractSimpleDataProvider.BaseData item) {
        category.setText(item.getText());
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
}
