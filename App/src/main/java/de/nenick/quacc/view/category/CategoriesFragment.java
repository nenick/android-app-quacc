package de.nenick.quacc.view.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.interval.GetAccountingIntervalsFunction;
import de.nenick.quacc.accounting.type.GetAccountingTypesFunction;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.mvp.BaseView;
import de.nenick.quacc.view.category.adapter.CategoryListAdapter;

@EFragment(R.layout.fragment_categories)
public class CategoriesFragment extends BasePresenterFragment {

    @Bean
    CategoriesView view;

    @Bean
    GetAccountingTypesFunction getAccountingTypesFunction;

    @Bean
    GetAccountingIntervalsFunction getAccountingIntervalsFunction;

    @Bean
    GetAccountingCategoriesFilteredFunction getAccountingCategoriesFilteredFunction;

    @Bean
    CategoryListAdapter categoryListAdapter;

    @Bean
    CreateCategoryFunction createCategoryFunction;

    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Override
    protected void onViewStart() {
        //view.setAccountingTypes(getAccountingTypesFunction.apply());
        //view.setAccountingCategories(getAccountingCategoriesFunction.apply());
        //view.setAccountingIntervals(getAccountingIntervalsFunction.apply());
        view.setListAdapter(categoryListAdapter);
    }

    @Click(R.id.confirm)
    protected void onConfirmNewCategory() {
        createCategoryFunction.apply(view.getAndClearLabel());
    }
}
