package de.nenick.quacc.view.accounting_edit;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.R;
import de.nenick.quacc.core.accounting.creation.CreateAccountingFunction;
import de.nenick.quacc.core.accounting.creation.CreateIntervalFunction;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.update.UpdateOnceOnlyAccountingFunction;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.valueparser.ParseValueFromStringFunction;

import static de.nenick.quacc.valueparser.ParseValueFromStringFunction.ParseResult.Successful;

@EBean
public class CreateOrUpdateAccountingFeature {

    @Bean
    CreateAccountingFunction createAccountingFunction;

    @Bean
    CreateIntervalFunction createIntervalFunction;

    @Bean
    UpdateOnceOnlyAccountingFunction updateOnceOnlyAccountingFunction;

    @Bean
    ParseValueFromStringFunction parseValueFromStringFunction;

    private long accountingId;
    private String initialInterval;
    EditAccountingView view;

    public void apply(long accountingId, String initialInterval, EditAccountingView view) {
        this.accountingId = accountingId;
        this.initialInterval = initialInterval;
        this.view = view;

        view.closeSoftKeyboard();

        ParseValueFromStringFunction.Result valueResult = parseValueFromStringFunction.apply(view.getValue());
        if (valueResult.report != Successful) {
            showParsingError(valueResult);
            return;
        }

        if (accountingId == 0) {
            createNewAccounting(valueResult);
        } else {
            updateAccounting(valueResult);
        }
        view.finish();
    }

    private void updateAccounting(ParseValueFromStringFunction.Result valueResult) {
        String account = view.getAccount();
        String accountingType = view.getAccountingType();
        String accountingInterval = view.getAccountingInterval();
        CategoryCursor accountingCategory = view.getAccountingCategory();
        String dateString = view.getDate();
        String comment = view.getComment();
        Date date = QuAccDateUtil.toDate(dateString);
        if (accountingInterval.equals(AccountingInterval.once.name()) && initialInterval.equals(AccountingInterval.once.name())) {
            updateOnceOnlyAccountingFunction.apply(accountingId, account, accountingType, accountingCategory.getId(), date, valueResult.value, comment);
        }
    }

    private void createNewAccounting(ParseValueFromStringFunction.Result valueResult) {
        String account = view.getAccount();
        String accountingType = view.getAccountingType();
        String accountingInterval = view.getAccountingInterval();
        CategoryCursor accountingCategory = view.getAccountingCategory();
        String dateString = view.getDate();
        String comment = view.getComment();
        Date date = QuAccDateUtil.toDate(dateString);
        if (accountingInterval.equals(AccountingInterval.once.name())) {
            createAccountingFunction.apply(account, accountingType, accountingInterval, accountingCategory.getId(), date, valueResult.value, comment);
        } else {
            if (view.isEndDateActive()) {
                String endDateString = view.getEndDate();
                Date endDate = QuAccDateUtil.toDate(endDateString);
                createIntervalFunction.applyWithEndDate(account, accountingType, accountingInterval, accountingCategory.getId(), date, endDate
                        , valueResult.value, comment);
            } else {
                createIntervalFunction.apply(account, accountingType, accountingInterval, accountingCategory.getId(), date, valueResult.value, comment);
            }
        }
    }

    private void showParsingError(ParseValueFromStringFunction.Result valueResult) {
        switch (valueResult.report) {
            case EmptyInput:
                view.showValueParsingError(R.string.parse_error_missing_value);
                break;
            case ZeroValue:
                view.showValueParsingError(R.string.parse_error_zero_value);
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
}
