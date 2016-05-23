package de.nenick.expandablerecyclerview;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorTreeAdapter;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

/**
 * Base cursor tree adapter for expandable recycler view.
 * <p/>
 * ExpandableItemAdapter requires stable IDs.
 * Implement the getGroupItemId() / getChildItemId() methods appropriately.
 *
 * @param <GVH>
 * @param <CVH>
 */
public abstract class ExpandableCursorTreeAdapter<GVH extends ExpandableCursorTreeAdapter.ListItemHolder, CVH extends ExpandableCursorTreeAdapter.ListItemHolder> extends AbstractExpandableItemAdapter<GVH, CVH> {

    public abstract static class ListItemHolder<T> extends AbstractExpandableItemViewHolder {
        public ListItemHolder(View itemView) {
            super(itemView);
        }

        public abstract void onBind(T content);
    }

    protected CursorTreeAdapter dataSource;

    public ExpandableCursorTreeAdapter(Context context) {
        setHasStableIds(true);

        dataSource = new CursorTreeAdapter(null, context, false) {
            @Override
            protected Cursor getChildrenCursor(Cursor groupCursor) {
                return ExpandableCursorTreeAdapter.this.getChildrenCursor(groupCursor);
            }

            @Override
            protected View newGroupView(Context context, Cursor cursor, boolean isExpanded, ViewGroup parent) {
                return null;
            }

            @Override
            protected void bindGroupView(View view, Context context, Cursor cursor, boolean isExpanded) {

            }

            @Override
            protected View newChildView(Context context, Cursor cursor, boolean isLastChild, ViewGroup parent) {
                return null;
            }

            @Override
            protected void bindChildView(View view, Context context, Cursor cursor, boolean isLastChild) {

            }
        };

        // notify self on real data source changes
        dataSource.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });
    }

    protected abstract Cursor getChildrenCursor(Cursor groupCursor);

    public void setChildrenCursor(int groupPosition, Cursor cursor) {
        // workaround for NullPointerException when group cursor was already cleaned
        // https://code.google.com/p/android/issues/detail?id=177590
        if(dataSource.getGroup(groupPosition) != null) {
            dataSource.setChildrenCursor(groupPosition, cursor);
        }
    }

    public void setGroupCursor(Cursor cursor) {
        dataSource.setGroupCursor(cursor);
        dataSource.notifyDataSetChanged(true);
    }

    @Override
    public void onBindGroupViewHolder(GVH holder, int groupPosition, int viewType) {
        //noinspection unchecked
        holder.onBind(dataSource.getGroup(groupPosition));
    }

    @Override
    public void onBindChildViewHolder(CVH holder, int groupPosition, int childPosition, int viewType) {
        //noinspection unchecked
        holder.onBind(dataSource.getChild(groupPosition, childPosition));
    }

    @Override
    public int getGroupCount() {
        return dataSource.getGroupCount();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return dataSource.getChildrenCount(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return dataSource.getGroupId(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return dataSource.getChildId(groupPosition, childPosition);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(GVH holder, int groupPosition, int x, int y, boolean expand) {
        return true;
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0;
    }
}
