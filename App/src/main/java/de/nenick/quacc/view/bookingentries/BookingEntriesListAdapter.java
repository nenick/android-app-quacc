package de.nenick.quacc.view.bookingentries;

import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;

import de.nenick.quacc.expandablerecyclerview.AbstractSimpleDataProvider;

public class BookingEntriesListAdapter extends AbstractExpandableItemAdapter<ListItemCategorySummery, ListItemBookingEntry> {

    private AbstractSimpleDataProvider mProvider;

    public BookingEntriesListAdapter(AbstractSimpleDataProvider dataProvider) {
        mProvider = dataProvider;

        // ExpandableItemAdapter requires stable ID, and also
        // have to implement the getGroupItemId()/getChildItemId() methods appropriately.
        setHasStableIds(true);
    }

    @Override
    public int getGroupCount() {
        return mProvider.getGroupCount();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mProvider.getChildCount(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mProvider.getGroupItem(groupPosition).getGroupId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mProvider.getChildItem(groupPosition, childPosition).getChildId();
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0; // only one group view class
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0; // only one child view class
    }

    @Override
    public ListItemCategorySummery onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return ListItemCategorySummery_.getInstance_(parent.getContext());
    }

    @Override
    public ListItemBookingEntry onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return ListItemBookingEntry_.getInstance_(parent.getContext());
    }

    @Override
    public void onBindGroupViewHolder(ListItemCategorySummery holder, int groupPosition, int viewType) {
        holder.bind(mProvider.getGroupItem(groupPosition));
    }

    @Override
    public void onBindChildViewHolder(ListItemBookingEntry holder, int groupPosition, int childPosition, int viewType) {
        holder.bind(mProvider.getChildItem(groupPosition, childPosition));
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(ListItemCategorySummery holder, int groupPosition, int x, int y, boolean expand) {
        // check the item is *not* pinned
        if (mProvider.getGroupItem(groupPosition).isPinned()) {
            // return false to raise View.OnClickListener#onClick() event
            return false;
        }

        // check is enabled
        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        return true;
    }
}
