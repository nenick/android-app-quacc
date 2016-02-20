package de.nenick.quacc.view.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.R;
import de.nenick.quacc.core.category.CreateCategoryFunction;
import de.nenick.quacc.core.category.GetAccountingCategoriesFilteredFunction;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.view.category.adapter.SectionAdapter;
import de.nenick.quacc.view.common.adapter.IntervalAdapter;
import de.nenick.quacc.view.common.adapter.TypeAdapter;
import de.nenick.quacc.view.category.adapter.CategoryListAdapter;
import de.nenick.quacc.core.bookinginterval.GetAccountingIntervalsFunction;
import de.nenick.quacc.core.bookingentry.direction.GetAccountingTypesFunction;
import de.nenick.quacc.view.mvp.BasePresenterFragment;
import de.nenick.quacc.view.mvp.BaseView;

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
    TypeAdapter typeAdapter;

    @Bean
    IntervalAdapter intervalAdapter;

    @Bean
    SectionAdapter sectionAdapter;

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
        view.setCategories(categoryListAdapter);

        typeAdapter.addAll(getAccountingTypesFunction.apply());
        view.setTypes(typeAdapter);

        intervalAdapter.addAll(getAccountingIntervalsFunction.apply());
        view.setIntervals(intervalAdapter);

        view.setSections(sectionAdapter);
    }

    @Click(R.id.confirm)
    protected void onConfirmNewCategory() {
        CategoryCursor categoryCursor = view.getSection();
        createCategoryFunction.apply(categoryCursor.getSection(), view.getAndClearName(), view.getInterval(), view.getType());
    }
}
