package de.nenick.quacc.view.bookingentries;

import android.database.Cursor;

import de.nenick.quacc.expandablerecyclerview.AbstractCursorDataProvider;

public class DbDataProvider extends AbstractCursorDataProvider {
    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildCount(int groupPosition) {
        return 0;
    }

    @Override
    public Cursor getGroupItem(int groupPosition) {
        return null;
    }

    @Override
    public Cursor getChildItem(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public void moveGroupItem(int fromGroupPosition, int toGroupPosition) {

    }

    @Override
    public void moveChildItem(int fromGroupPosition, int fromChildPosition, int toGroupPosition, int toChildPosition) {

    }

    @Override
    public void removeGroupItem(int groupPosition) {

    }

    @Override
    public void removeChildItem(int groupPosition, int childPosition) {

    }

    @Override
    public long undoLastRemoval() {
        return 0;
    }
}
