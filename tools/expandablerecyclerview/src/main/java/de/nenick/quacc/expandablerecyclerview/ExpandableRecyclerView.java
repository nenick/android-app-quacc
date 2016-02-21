package de.nenick.quacc.expandablerecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;

public class ExpandableRecyclerView extends RecyclerView implements RecyclerViewExpandableItemManager.OnGroupCollapseListener,
        RecyclerViewExpandableItemManager.OnGroupExpandListener {

    private RecyclerViewExpandableItemManager recyclerViewExpandableItemManager;
    private Adapter adapter;

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
        if(adapter != null) {
            // wrap to support expanding adapter
            adapter = recyclerViewExpandableItemManager.createWrappedAdapter(adapter);
            recyclerViewExpandableItemManager.attachRecyclerView(this);
        }
        this.adapter = adapter;
        super.setAdapter(adapter);
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
        SavedState savedState = (SavedState) state;

        final Parcelable eimSavedState = savedState.expansionState;
        recyclerViewExpandableItemManager.restoreState(eimSavedState);
        super.onRestoreInstanceState(savedState.getSuperState());

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState, recyclerViewExpandableItemManager);

        Parcelable expansionState = recyclerViewExpandableItemManager.getSavedState();
        savedState.expansionState = expansionState;

        return savedState;
    }

    static class SavedState extends BaseSavedState {

        private RecyclerViewExpandableItemManager recyclerViewExpandableItemManager;
        public Parcelable expansionState;

        public SavedState(Parcelable superState, RecyclerViewExpandableItemManager recyclerViewExpandableItemManager) {
            super(superState);
            this.recyclerViewExpandableItemManager = recyclerViewExpandableItemManager;
        }

        private SavedState(Parcel in) {
            super(in);
            expansionState = in.readParcelable(getClass().getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeParcelable(recyclerViewExpandableItemManager.getSavedState(), 0);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
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
    }
}
