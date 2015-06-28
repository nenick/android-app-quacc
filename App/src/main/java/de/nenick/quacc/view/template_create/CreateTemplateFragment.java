package de.nenick.quacc.view.template_create;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import de.nenick.quacc.R;
import de.nenick.quacc.core.template.CreateTemplateMatchFunction;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.view.accounting_create.adapter.CategoryAdapter;
import de.nenick.quacc.view.common.adapter.IntervalAdapter;
import de.nenick.quacc.view.common.adapter.TypeAdapter;
import de.nenick.quacc.core.account.GetAccountsFunction;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.interval.GetAccountingIntervalsFunction;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.core.accounting.type.GetAccountingTypesFunction;
import de.nenick.quacc.view.mvp.BasePresenterFragment;
import de.nenick.quacc.view.mvp.BaseView;
import de.nenick.quacc.valueparser.ParseValueFromStringFunction;
import de.nenick.quacc.core.template.CreateAccountingTemplateFunction;

@EFragment(R.layout.fragment_create_accounting_template)
@OptionsMenu(R.menu.menu_create_account)
public class CreateTemplateFragment extends BasePresenterFragment {

    @Bean
    CreateTemplateView view;

    @Bean
    GetAccountingIntervalsFunction getAccountingIntervalsFunction;

    @Bean
    GetAccountingTypesFunction getAccountingTypesFunction;

    @Bean
    GetAccountsFunction getAccountsFunction;

    @Bean
    CreateAccountingTemplateFunction createAccountingTemplateFunction;

    @Bean
    ParseValueFromStringFunction parseValueFromStringFunction;

    @Bean
    CreateTemplateMatchFunction createTemplateMatchFunction;

    @Bean
    IntervalAdapter intervalAdapter;

    @Bean
    TypeAdapter typeAdapter;

    @Bean
    CategoryAdapter categoryAdapter;

    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Override
    protected void onViewStart() {
        view.setAccounts(getAccountsFunction.apply());

        intervalAdapter.addAll(getAccountingIntervalsFunction.apply());
        view.setAccountingIntervals(intervalAdapter);
        view.setAccountingInterval(AccountingInterval.once.name());

        typeAdapter.addAll(getAccountingTypesFunction.apply());
        view.setAccountingTypes(typeAdapter);
        view.setAccountingType(AccountingType.outgoing.name());

        view.setAccountingCategories(categoryAdapter);
        reloadCategories();

    }

    @OptionsItem(R.id.confirm)
    protected void onConfirmNewAccounting() {
        view.closeSoftKeyboard();

        String account = view.getAccount();
        String accountingType = view.getAccountingType();
        String accountingInterval = view.getAccountingInterval();
        CategoryCursor accountingCategory = view.getAccountingCategory();
        String comment = view.getComment();
        view.finish();
        long templateId = createAccountingTemplateFunction.apply(account, accountingType, accountingInterval, accountingCategory.getId(), comment);

        String speechText = view.getSpeechText();
        if(!speechText.isEmpty()) {
            createTemplateMatchFunction.apply(speechText, templateId);
        }
    }

    @ItemSelect(R.id.interval)
    public void onIntervalSelection(boolean selected, int position) {
        reloadCategories();
    }

    @ItemSelect(R.id.type)
    public void onTypeSelection(boolean selected, int position) {
        reloadCategories();
    }

    private void reloadCategories() {
        String accountingInterval = view.getAccountingInterval();
        String accountingType = view.getAccountingType();
        if (isViewNotFullInitialized(accountingInterval, accountingType)) {
            return;
        }
        categoryAdapter.updateFor(accountingInterval, accountingType);
    }

    private boolean isViewNotFullInitialized(String accountingInterval, String accountingType) {
        return accountingInterval == null || accountingType == null;
    }
}
