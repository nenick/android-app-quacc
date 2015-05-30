package de.nenick.quacc.accounting.create;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.accounting.create.functions.GetAccountingCategoriesFunction;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.i18n.AccountingIntervalTranslator;

@EBean
public class CategoryAdapter extends BaseAdapter {

    @Bean
    AccountingIntervalTranslator accountingIntervalTranslator;

    @Bean
    GetAccountingCategoriesFunction getAccountingCategoriesFunction;

    @RootContext
    Context context;

    boolean isDropDownView = false;

    private String lastInterval;
    private String lastType;
    private CategoryCursor cursor;

    public void updateFor(String interval, String type) {
        if(interval.equals(lastInterval) && type.equals(lastType)) return;
        cursor = getAccountingCategoriesFunction.apply(interval, type);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(cursor == null) return 0;
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        cursor.moveToPosition(i);
        return cursor.getName();
    }

    @Override
    public long getItemId(int i) {
        cursor.moveToPosition(i);
        return cursor.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = newView(context);
        } else {
            v = convertView;
        }
        bindView(v, position, cursor);
        return v;
    }

    public View newView(Context context) {
        return CategoryItemView_.build(context);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        isDropDownView = true;
        View dropDownView = super.getDropDownView(position, convertView, parent);
        isDropDownView = false;
        return dropDownView;
    }

    public void bindView(View view, int position, Cursor cursor) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("couldn't move cursor to position " + position);
        }
        CategoryItemView textView = (CategoryItemView) view;
        CategoryCursor categoryCursor = (CategoryCursor) cursor;
        textView.setText(categoryCursor.getName());
        if (categoryCursor.getLevel() > 0 && isDropDownView) {
            textView.setSubCategory();
        } else {
            textView.setMainCategory();
        }
    }
}
