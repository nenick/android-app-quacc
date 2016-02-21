package de.nenick.quacc.expandablerecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

public class ExpandableRecyclerView extends RecyclerView {

    private RecyclerViewExpandableItemManager recyclerViewExpandableItemManager;

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

    private void initExpansionTools() {

    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());

        final Parcelable eimSavedState = savedState.expansionState;
        recyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(eimSavedState);
        //recyclerViewExpandableItemManager.setOnGroupExpandListener(this);
        //recyclerViewExpandableItemManager.setOnGroupCollapseListener(this);
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
}
