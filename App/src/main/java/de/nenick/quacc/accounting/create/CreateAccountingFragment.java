package de.nenick.quacc.accounting.create;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import java.util.Date;
import java.util.Objects;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.functions.CreateAccountingFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountingCategoriesFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountingIntervalsFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountingTypesFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountsFunction;
import de.nenick.quacc.accounting.create.functions.ParseAccountingValueFunction;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountingInterval;
import de.nenick.quacc.database.AccountingType;

import static de.nenick.quacc.accounting.create.functions.ParseAccountingValueFunction.ParseResult.Successful;

@EFragment(R.layout.fragment_create_accounting)
@OptionsMenu(R.menu.menu_create_account)
public class CreateAccountingFragment extends BasePresenterFragment {

    @Bean
    CreateAccountingView view;

    @Bean
    GetAccountingCategoriesFunction getAccountingCategoriesFunction;

    @Bean
    GetAccountingIntervalsFunction getAccountingIntervalsFunction;

    @Bean
    GetAccountingTypesFunction getAccountingTypesFunction;

    @Bean
    GetAccountsFunction getAccountsFunction;

    @Bean
    CreateAccountingFunction createAccountingFunction;

    @Bean
    ParseAccountingValueFunction parseAccountingValueFunction;

    @Bean
    SpeechRecognitionFeature speechRecognitionFeature;

    @Bean
    AccountingIntervalAdapter accountingIntervalAdapter;

    @Bean
    AccountingTypeAdapter accountingTypeAdapter;

    @Override
    protected void onViewStart() {
        speechRecognitionFeature.setView(view);

        view.showAccountingIntervals(accountingIntervalAdapter);
        accountingIntervalAdapter.addAll(getAccountingIntervalsFunction.apply());
        view.setAccountingInterval(AccountingInterval.once.name());

        view.showAccountingTypes(accountingTypeAdapter);
        accountingTypeAdapter.addAll(getAccountingTypesFunction.apply());
        view.setAccountingType(AccountingType.outgoing.name());

        view.showAccounts(getAccountsFunction.apply());
        view.showDate(QuAccDateUtil.currentDate());
        reloadCategories();
    }

    @Override
    protected void onViewPause() {
        speechRecognitionFeature.onViewPause();
    }

    @Override
    protected void onViewFinish() {
        speechRecognitionFeature.onViewFinish();
    }

    @OptionsItem(R.id.confirm)
    protected void onConfirmNewAccounting() {
        view.closeSoftKeyboard();
        String value = view.getValue();
        ParseAccountingValueFunction.Result valueResult = parseAccountingValueFunction.apply(value);
        if (valueResult.report == Successful) {
            String account = view.getAccount();
            String accountingType = view.getAccountingType();
            String accountingInterval = view.getAccountingInterval();
            String accountingCategory = view.getAccountingCategory();
            String dateString = view.getDate();
            String comment = view.getComment();
            view.finish();
            Date date = QuAccDateUtil.parse(dateString);
            createAccountingFunction.apply(account, accountingType, accountingInterval, accountingCategory, date, valueResult.value, comment);
        } else {
            showParsingError(valueResult);
        }
    }

    private void showParsingError(ParseAccountingValueFunction.Result valueResult) {
        switch (valueResult.report) {
            case EmptyInput:
                view.showValueParsingError(R.string.parse_error_missing_value);
                break;
            case ZeroValue:
                view.showValueParsingError(R.string.parse_error_missing_value);
                break;
            case InvalidChar:
                view.showValueParsingError(R.string.parse_error_invalid_char);
                break;
            case InvalidFormat:
                view.showValueParsingError(R.string.parse_error_invalid_format);
                break;
            case UnknownError:
            default:
                view.showValueParsingError(R.string.parse_error_unknown);
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
        if(isViewNotFullInitialized(accountingInterval, accountingType)) {
            return;
        }
        CharSequence[] categories = getAccountingCategoriesFunction.apply(accountingType, accountingInterval);
        view.showAccountingCategories(categories);
    }

    private boolean isViewNotFullInitialized(String accountingInterval, String accountingType) {
        return accountingInterval == null || accountingType == null;
    }
}
