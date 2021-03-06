package de.nenick.quacc.view.category.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.core.category.GetCategoriesCursorFunction;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class CategoryListAdapter extends CursorAdapter {

    @RootContext
    Context context;

    @Bean
    GetCategoriesCursorFunction getCategoriesCursorFunction;

    public CategoryListAdapter() {
        super(null, null, true);
    }

    @AfterInject
    protected void afterInject() {
        mContext = context;
        swapCursor(getCategoriesCursorFunction.apply());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return CategoryListItem_.build(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        bindView((CategoryListItem) view, (CategoryCursor) cursor);
    }

    private void bindView(CategoryListItem view, CategoryCursor cursor) {
        view.setInterval(cursor.getInterval());
        view.setSection(cursor.getSection());
        view.setType(cursor.getDirection());
        view.setCategory(cursor.getName());
    }
}
