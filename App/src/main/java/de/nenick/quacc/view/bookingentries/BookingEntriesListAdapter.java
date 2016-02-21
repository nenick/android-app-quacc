package de.nenick.quacc.view.bookingentries;

import android.database.DataSetObserver;
import android.view.ViewGroup;
import android.widget.CursorTreeAdapter;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;

import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;

class BookingEntriesListAdapter extends AbstractExpandableItemAdapter<ListItemCategorySummery, ListItemBookingEntry> {

    private CursorTreeAdapter mProvider;

    public BookingEntriesListAdapter(CursorTreeAdapter dataProvider) {
        mProvider = dataProvider;

        // ExpandableItemAdapter requires stable ID, and also
        // have to implement the getGroupItemId()/getChildItemId() methods appropriately.
        setHasStableIds(true);

        mProvider.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getGroupCount() {
        return mProvider.getGroupCount();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mProvider.getChildrenCount(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mProvider.getGroupId(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mProvider.getChildId(groupPosition, childPosition);
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
        holder.bind((BookingEntryCursor) mProvider.getGroup(groupPosition));
    }

    @Override
    public void onBindChildViewHolder(ListItemBookingEntry holder, int groupPosition, int childPosition, int viewType) {
        holder.bind((BookingEntryCursor)mProvider.getChild(groupPosition, childPosition));
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(ListItemCategorySummery holder, int groupPosition, int x, int y, boolean expand) {
        // check the item is *not* pinned
        /*if (mProvider.getGroupItem(groupPosition).isPinned()) {
            // return false to raise View.OnClickListener#onClick() event
            return false;
        }*/

        // check is enabled
        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        return true;
    }
}
