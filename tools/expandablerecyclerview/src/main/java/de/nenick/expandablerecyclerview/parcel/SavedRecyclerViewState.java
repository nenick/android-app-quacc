package de.nenick.expandablerecyclerview.parcel;

import android.os.Parcel;
import android.os.Parcelable;

public class SavedRecyclerViewState implements Parcelable {

    private final Parcelable expansionState;
    private final Parcelable superState;

    public SavedRecyclerViewState(Parcelable superState, Parcelable expansionState) {
        this.superState = superState;
        this.expansionState = expansionState;
    }

    private SavedRecyclerViewState(Parcel in) {
        superState = in.readParcelable(getClass().getClassLoader());
        expansionState = in.readParcelable(getClass().getClassLoader());
    }

    public Parcelable getExpansionState() {
        return expansionState;
    }

    public Parcelable getSuperState() {
        return superState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(superState, 0);
        out.writeParcelable(expansionState, 0);
    }

    public static final Creator<SavedRecyclerViewState> CREATOR = new Creator<SavedRecyclerViewState>() {
        @Override
        public SavedRecyclerViewState createFromParcel(Parcel in) {
            return new SavedRecyclerViewState(in);
        }

        @Override
        public SavedRecyclerViewState[] newArray(int size) {
            return new SavedRecyclerViewState[size];
        }
    };
}
