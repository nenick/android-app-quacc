package de.nenick.quacc.view.category.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.core.category.GetAccountingCategoriesCursorFunction;
import de.nenick.quacc.core.category.GetCategorySectionsFunction;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class SectionAdapter extends CursorAdapter {

    @RootContext
    Context context;

    @Bean
    GetCategorySectionsFunction getCategorySectionsFunction;

    public SectionAdapter() {
        super(null, null, true);
    }

    @AfterInject
    protected void afterInject() {
        mContext = context;
        swapCursor(getCategorySectionsFunction.apply());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView)view.findViewById(android.R.id.text1)).setText(((CategoryCursor)cursor).getSection());
    }
}
