package de.nenick.quacc.view.mvp;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Some defaults for cursor based adapters.
 *
 * Implement
 * * newView new view for the adapter view (general for all kinds like list items or dropdown items)
 * * bindView bind data for adapter view (simple list items, selected spinner item)
 * * bindDropDownView bind data for adapter view in special drop down mode. can be the same like bindView it is your decision.
 * <p/>
 * The original cursor adapter is not supported by robolectric
 */
@EBean
public abstract class BaseCursorPresenterAdapter<C extends Cursor, V extends View> extends BaseAdapter {

    @RootContext
    protected Context context;

    protected C cursor;
    private boolean isDropDownView;

    public void update(C newCursor) {
        if(cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        V v;
        if (convertView == null) {
            cursor.moveToPosition(position);
            v = newView(context, cursor);
        } else {
            //noinspection unchecked this base adapter is designed to have only one kind of view of type V
            v = (V) convertView;
        }
        cursor.moveToPosition(position);
        if(isDropDownView) {
            bindDropDownView(v, position, cursor);
        } else {
            bindView(v, position, cursor);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        isDropDownView = true;
        View dropDownView = super.getDropDownView(position, convertView, parent);
        isDropDownView = false;
        return dropDownView;
    }

    protected abstract V newView(Context context, C cursor);

    protected abstract void bindView(V view, int position, C cursor);

    protected void bindDropDownView(V view, int position, C cursor) {}
}
