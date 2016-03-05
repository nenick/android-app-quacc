package de.nenick.quacc.view.template_create;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import de.nenick.quacc.R;
import de.nenick.quacc.core.template.AddTemplateKeywordFunction;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.view.accounting_edit.adapter.CategoryAdapter;
import de.nenick.quacc.view.common.adapter.IntervalAdapter;
import de.nenick.quacc.view.common.adapter.TypeAdapter;
import de.nenick.quacc.core.account.GetAccountsFunction;
import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
import de.nenick.quacc.core.bookinginterval.GetAccountingIntervalsFunction;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.bookingentry.direction.GetAccountingTypesFunction;
import de.nenick.quacc.view.mvp.BasePresenterFragment;
import de.nenick.quacc.view.mvp.BaseView;
import de.nenick.quacc.valueparser.ParseValueFromStringFunction;
import de.nenick.quacc.core.template.CreateBookingTemplateFunction;

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
    CreateBookingTemplateFunction createBookingTemplateFunction;

    @Bean
    ParseValueFromStringFunction parseValueFromStringFunction;

    @Bean
    AddTemplateKeywordFunction addTemplateKeywordFunction;

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
        view.setAccountingInterval(BookingIntervalOption.once.name());

        typeAdapter.addAll(getAccountingTypesFunction.apply());
        view.setAccountingTypes(typeAdapter);
        view.setAccountingType(BookingDirectionOption.outgoing.name());

        view.setAccountingCategories(categoryAdapter);
        reloadCategories();

    }

    @OptionsItem(R.id.confirm)
    protected void onConfirmNewAccounting() {
        view.closeSoftKeyboard();

        String account = view.getAccount();
        String accountingType = view.getDirection();
        String accountingInterval = view.getAccountingInterval();
        CategoryCursor accountingCategory = view.getAccountingCategory();
        String comment = view.getComment();
        view.finish();

        long templateId = createBookingTemplateFunction.apply(account, accountingType, accountingInterval, accountingCategory.getId(), comment);

        String speechText = view.getSpeechText();
        if(!speechText.isEmpty()) {
            addTemplateKeywordFunction.apply(speechText, templateId);
        }
    }

    @ItemSelect(R.id.interval)
    public void onIntervalSelection(boolean selected, int position) {
        reloadCategories();
    }

    @ItemSelect(R.id.direction)
    public void onTypeSelection(boolean selected, int position) {
        reloadCategories();
    }

    private void reloadCategories() {
        String accountingInterval = view.getAccountingInterval();
        String accountingType = view.getDirection();
        if (isViewNotFullInitialized(accountingInterval, accountingType)) {
            return;
        }
        categoryAdapter.updateFor(accountingInterval, accountingType);
    }

    private boolean isViewNotFullInitialized(String accountingInterval, String accountingType) {
        return accountingInterval == null || accountingType == null;
    }
}
