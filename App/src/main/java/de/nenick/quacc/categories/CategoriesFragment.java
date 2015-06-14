package de.nenick.quacc.categories;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.functions.GetAccountingCategoriesFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountingIntervalsFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountingTypesFunction;
import de.nenick.quacc.categories.functions.CreateCategoryFunction;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.mvp.BaseView;

@EFragment(R.layout.fragment_categories)
public class CategoriesFragment extends BasePresenterFragment {

    @Bean
    CategoriesView view;

    @Bean
    GetAccountingTypesFunction getAccountingTypesFunction;

    @Bean
    GetAccountingIntervalsFunction getAccountingIntervalsFunction;

    @Bean
    GetAccountingCategoriesFunction getAccountingCategoriesFunction;

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
