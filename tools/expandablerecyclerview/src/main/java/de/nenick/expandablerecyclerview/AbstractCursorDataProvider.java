package de.nenick.expandablerecyclerview;

import android.database.Cursor;

public abstract class AbstractCursorDataProvider {

    public abstract int getGroupCount();
    public abstract int getChildCount(int groupPosition);

    public abstract Cursor getGroupItem(int groupPosition);
    public abstract Cursor getChildItem(int groupPosition, int childPosition);

    public abstract void moveGroupItem(int fromGroupPosition, int toGroupPosition);
    public abstract void moveChildItem(int fromGroupPosition, int fromChildPosition, int toGroupPosition, int toChildPosition);

    public abstract void removeGroupItem(int groupPosition);
    public abstract void removeChildItem(int groupPosition, int childPosition);

    public abstract long undoLastRemoval();
}
