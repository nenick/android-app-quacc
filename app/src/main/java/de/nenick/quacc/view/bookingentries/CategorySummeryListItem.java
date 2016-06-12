package de.nenick.quacc.view.bookingentries;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;

import de.nenick.expandablerecyclerview.ExpandableCursorTreeAdapter;
import de.nenick.expandablerecyclerview.indicator.ExpandableItemIndicator;
import de.nenick.quacc.R;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.bookingentry.CategorySummeryCursor;
import de.nenick.quacc.tools.AmountParser;

/**
 * Group for expandable list view.
 */
@EBean
class CategorySummeryListItem extends ExpandableCursorTreeAdapter.ListItemHolder<CategorySummeryCursor> {

    @EViewGroup(R.layout.item_accounting_group)
    protected static class ListItemView extends FrameLayout {
        ListItemView(Context context) {
            super(context);
        }

        static ListItemView create(Context context) {
            return CategorySummeryListItem_.ListItemView_.build(context);
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

    @Bean
    AmountParser amountParser;

    public static CategorySummeryListItem create(Context context) {
        return CategorySummeryListItem_.getInstance_(context);
    }

    public CategorySummeryListItem(Context context) {
        super(ListItemView.create(context));
        injectViewComponents();
    }

    @Override
    public void onBind(CategorySummeryCursor item) {
        syncExpansionIndicator();
        itemView.setClickable(true);

        date.setText(QuAccDateUtil.toString(item.getDateStart()));
        category.setText(item.getCategoryName());
        amount.setText(amountParser.asString(item.getAmount()));
        endDate.setText(QuAccDateUtil.toString(item.getDateEnd()));

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

    private void syncExpansionIndicator() {
        final int expandState = getExpandStateFlags();
        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {
            boolean isExpanded = (expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0;
            boolean animateIndicator = ((expandState & ExpandableItemConstants.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);
            expandableItemIndicator.setExpandedState(isExpanded, animateIndicator);
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
        //itemView.setBackgroundColor(color);
        date.setTextColor(color2);
        dateSeparator.setTextColor(color2);
        endDate.setTextColor(color2);
        category.setTextColor(color3);
        amount.setTextColor(color3);
    }
}
