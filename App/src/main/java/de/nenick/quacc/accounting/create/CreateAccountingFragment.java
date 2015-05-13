package de.nenick.quacc.accounting.create;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import java.util.Date;

import de.nenick.quacc.R;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.core.accounting.AddNewAccountingUc;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.accounting.ParseAccountingValueUc;
import de.nenick.quacc.common.util.QuAccDateFormatUtil;

import static de.nenick.quacc.core.accounting.ParseAccountingValueUc.ParseResult.Successful;

@EFragment(R.layout.fragment_add_accounting)
@OptionsMenu(R.menu.menu_add_account)
public class CreateAccountingFragment extends BasePresenterFragment {

    @Bean
    CreateAccountingView view;

    @Bean
    GetAccountingCategoriesUc getAccountingCategoriesUc;

    @Bean
    GetAccountingIntervalsUc getAccountingIntervalsUc;

    @Bean
    GetAccountingTypesUc getAccountingTypesUc;

    @Bean
    GetAccountsUc getAccountsUc;

    @Bean
    AddNewAccountingUc addNewAccountingUc;

    @Bean
    ParseAccountingValueUc parseAccountingValueUc;

    @Bean
    SpeechRecognitionFeature speechRecognitionFeature;

    @Override
    protected void onViewStart() {
        speechRecognitionFeature.setView(view);

        view.showAccounts(getAccountsUc.apply());
        view.showAccountingTypes(getAccountingTypesUc.apply());
        view.showAccountingIntervals(getAccountingIntervalsUc.apply());
        view.showAccountingCategories(getAccountingCategoriesUc.apply());
        view.showDate(QuAccDateFormatUtil.currentDate());
    }

    @Override
    protected void onViewPause() {
        speechRecognitionFeature.stop();
    }

    @Override
    protected void onViewFinish() {
        speechRecognitionFeature.destroy();
    }

    @OptionsItem(R.id.confirm)
    protected void onConfirmButton() {
        view.closeSoftKeyboard();
        String account = view.getAccount();
        String accountingType = view.getAccountingType();
        String accountingInterval = view.getAccountingInterval();
        String accountingCategory = view.getAccountingCategory();
        String dateString = view.getDate();
        String value = view.getValue();
        String comment = view.getComment();

        ParseAccountingValueUc.Result valueResult = parseAccountingValueUc.apply(value);
        if (valueResult.report == Successful) {
            Date date = QuAccDateFormatUtil.parse(dateString);
            addNewAccountingUc.apply(account, accountingType, accountingInterval, accountingCategory, date, valueResult.value, comment);
            view.finish();
        } else {
            switch (valueResult.report) {
                case DotAndCommaMix:
                    view.showValueParsingError(R.string.parse_error_mix_dot_and_comma);
                    break;
                case UnknownError:
                    view.showValueParsingError(R.string.parse_error_unknown);
            }

        }
    }
}
