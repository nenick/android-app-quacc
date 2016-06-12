package de.nenick.expandablerecyclerview;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import de.nenick.expandablerecyclerview.parcel.SavedRecyclerViewState;

public class ExpandableRecyclerView extends RecyclerView implements RecyclerViewExpandableItemManager.OnGroupCollapseListener,
        RecyclerViewExpandableItemManager.OnGroupExpandListener {

    private RecyclerViewExpandableItemManager recyclerViewExpandableItemManager;
    private Parcelable pendingSavedState;

    public ExpandableRecyclerView(Context context) {
        super(context);
        initExpansionTools();
    }

    public ExpandableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initExpansionTools();
    }

    public ExpandableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initExpansionTools();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            // wrap to support expanding adapter
            adapter = recyclerViewExpandableItemManager.createWrappedAdapter(adapter);
            recyclerViewExpandableItemManager.attachRecyclerView(this);
        }
        super.setAdapter(adapter);

        // restore RecyclerView state after all initialisation is done
        if(pendingSavedState != null) {
            recyclerViewExpandableItemManager.restoreState(pendingSavedState);
            pendingSavedState = null;
        }
    }

    private void initExpansionTools() {
        setLayoutManager(new LinearLayoutManager(getContext()));

        // Change animations are enabled by default since support-v7-recyclerview v22.
        // Need to disable them when using animation indicator.
        final GeneralItemAnimator animator = new RefactoredDefaultItemAnimator();
        animator.setSupportsChangeAnimations(false);
        setItemAnimator(animator);

        // expandable is by definition not fixed in size
        setHasFixedSize(false);

        recyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(null);
        recyclerViewExpandableItemManager.setOnGroupExpandListener(this);
        recyclerViewExpandableItemManager.setOnGroupCollapseListener(this);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedRecyclerViewState savedRecyclerViewState = (SavedRecyclerViewState) state;
        pendingSavedState = savedRecyclerViewState.getExpansionState();
        super.onRestoreInstanceState(savedRecyclerViewState.getSuperState());
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        Parcelable expandableState = recyclerViewExpandableItemManager.getSavedState();
        return new SavedRecyclerViewState(superState, expandableState);
    }

    @Override
    public void onGroupCollapse(int groupPosition, boolean fromUser) {
    }

    @Override
    public void onGroupExpand(int groupPosition, boolean fromUser) {
        if (fromUser) {
            adjustScrollPositionOnGroupExpanded(groupPosition);
        }
    }

    private void adjustScrollPositionOnGroupExpanded(int groupPosition) {
        /*int childItemHeight = getActivity().getResources().getDimensionPixelSize(R.dimen.list_item_height);
        int topMargin = (int) (getActivity().getResources().getDisplayMetrics().density * 16); // top-spacing: 16dp
        int bottomMargin = topMargin; // bottom-spacing: 16dp

        mRecyclerViewExpandableItemManager.scrollToGroup(groupPosition, childItemHeight, topMargin, bottomMargin);
        */
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        /*
        if (recyclerViewExpandableItemManager != null) {
            recyclerViewExpandableItemManager.release();
            recyclerViewExpandableItemManager = null;
        }

        if (adapter != null) {
            WrapperAdapterUtils.releaseAll(adapter);
            adapter = null;
        }
        setItemAnimator(null);
        setAdapter(null);
        */
    }
}
