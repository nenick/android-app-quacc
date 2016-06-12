# Expandable RecyclerView with Cursors

Uses https://github.com/h6ah4i/android-advancedrecyclerview for expandable RecyclerView functions.
Add abstract `ExpandableCursorTreeAdapter` to provide content from a cursor.

## Example usage (with AndroidAnnotations)

### Create group and child item

**Create group layout item_group.xml**

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <de.nenick.expandablerecyclerview.indicator.ExpandableItemIndicator
            android:id="@+id/indicator"
            android:layout_width="24dp"
            android:layout_height="24dp"
            android:layout_alignParentEnd="true" />

        <!-- your item views -->


    </RelativeLayout>

**Create child layout item_child.xml**

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <!-- your item views -->

    </RelativeLayout>

**Create group item class**

    @EBean
    public class GroupItem extends ExpandableCursorTreeAdapter.ListItemHolder<Cursor> {

        @EViewGroup(R.layout.item_group)
        protected static class ItemView extends FrameLayout {

            static ItemView create(Context context) {
                return GroupItem_.ItemView_.build(context);
            }

            ItemView(Context context) {
                super(context);
            }
        }

        @ViewById(R.id.indicator)
        protected ExpandableItemIndicator expandableItemIndicator;

        @Override
        public void onBind(Cursor item) {
            syncExpansionIndicator();

            // make the whole item clickable to expand/collapse it
            itemView.setClickable(true);

            // fill views with content
        }

        private void syncExpansionIndicator() {
            final int expandState = getExpandStateFlags();
            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {
                boolean isExpanded = (expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0;
                boolean animateIndicator = ((expandState & ExpandableItemConstants.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);
                expandableItemIndicator.setExpandedState(isExpanded, animateIndicator);
            }
        }
    }

**Create child item class**

    @EBean
    public class ChildItem extends ExpandableCursorTreeAdapter.ListItemHolder<Cursor> {

        @EViewGroup(R.layout.item_child)
        protected static class ItemView extends FrameLayout {

            static ItemView create(Context context) {
                return ChildItem_.ItemView_.build(context);
            }

            ItemView(Context context) {
                super(context);
            }
        }

        @Override
        public void onBind(Cursor item) {
            // fill views with content
        }
    }

### Create cursor adapter

    @EBean
    public class MyAdapter extends ExpandableCursorTreeAdapter<GroupItem, ChildItem> {

        public BookingEntriesListAdapter(Context context) {
            super(context);
        }

        public void update() {
            // start group cursor loader
            // call setGroupCursor(groupCursor); when done
        }

        @Override
        protected Cursor getChildrenCursor(Cursor groupCursor) {
            final int groupPosition = groupCursor.getPosition();

            // start children cursor loader
            // call setChildrenCursor(groupPosition, childCursor); when done

            // return null when using cursor loader
            return null;
        }

        @Override
        public GroupItem onCreateGroupViewHolder(ViewGroup parent, int viewType) {
            return GroupItem.create(parent.getContext());
        }

        @Override
        public ChildItem onCreateChildViewHolder(ViewGroup parent, int viewType) {
            return ChildItem.create(parent.getContext());
        }
    }


### Use expandable RecyclerView

**Add to layout**

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <de.nenick.expandablerecyclerview.indicator.ExpandableItemIndicator
                android:id="@+id/indicator"
                android:layout_width="24dp"
                android:layout_height="24dp"
                android:layout_gravity="end|center_vertical"
                android:layout_marginEnd="16dp"/>

    </RelativeLayout>

**Add to Activity/Fragment**

Use the LazyAdapter tool for proper restoring instance state.

    @ViewById(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @Bean
    protected MyAdapter myAdapter

    @AfterViews
    protected void onAfterViewsCreated() {
        LazyAdapter.inject(recyclerView, adapter);
        adapter.update();
    }