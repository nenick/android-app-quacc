package de.nenick.quacc.accounting.create;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.accounting.create.functions.GetAccountingCategoriesFunction;
import de.nenick.quacc.common.mvp.BaseCursorPresenterAdapter;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.i18n.AccountingIntervalTranslator;

@EBean
public class CategoryAdapter extends BaseCursorPresenterAdapter<CategoryCursor, CategoryItemView> {

    @Bean
    AccountingIntervalTranslator accountingIntervalTranslator;

    @Bean
    GetAccountingCategoriesFunction getAccountingCategoriesFunction;

    public void updateFor(String interval, String type) {
        update(getAccountingCategoriesFunction.apply(interval, type));
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
    public CategoryItemView newView(Context context, CategoryCursor cursor) {
        return CategoryItemView_.build(context);
    }

    @Override
    public void bindView(CategoryItemView view, int position, CategoryCursor cursor) {
        view.setText(cursor.getName());
        view.setMainCategory();
    }

    @Override
    protected void bindDropDownView(CategoryItemView view, int position, CategoryCursor cursor) {
        bindView(view, position, cursor);
        if (cursor.getLevel() > 0) {
            view.setSubCategory();
        } else {
            view.setMainCategory();
        }
    }
}
