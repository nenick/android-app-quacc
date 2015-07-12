package de.nenick.quacc.view.accounting_edit;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import de.nenick.quacc.R;
import de.nenick.quacc.core.account.GetAccountsFunction;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.accounting.AccountingDb;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.view.accounting_edit.adapter.CategoryAdapter;
import de.nenick.quacc.view.accounting_edit.speechrecognition.SpeechRecognitionFeature;
import de.nenick.quacc.view.common.adapter.IntervalAdapter;
import de.nenick.quacc.view.common.adapter.TypeAdapter;
import de.nenick.quacc.view.mvp.BasePresenterFragment;
import de.nenick.quacc.view.mvp.BaseView;

@EFragment(R.layout.fragment_create_accounting)
@OptionsMenu(R.menu.menu_create_account)
public class EditAccountingFragment extends BasePresenterFragment {

    @FragmentArg
    long accountingId;

    String accountingIntervalInitial;

    @Bean
    EditAccountingView view;

    @Bean
    GetAccountsFunction getAccountsFunction;

    @Bean
    ParseValueFromIntegerFunction parseValueFromIntegerFunction;

    @Bean
    SpeechRecognitionFeature speechRecognitionFeature;

    @Bean
    IntervalAdapter intervalAdapter;

    @Bean
    TypeAdapter typeAdapter;

    @Bean
    CategoryAdapter categoryAdapter;

    @Bean
    AccountingDb accountingDb;

    @Bean
    CreateOrUpdateAccountingFeature createOrUpdateAccountingFeature;

    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Override
    protected void onViewStart() {
        speechRecognitionFeature.setView(view);

        view.setDate(QuAccDateUtil.currentDate());
        view.setEndDate(QuAccDateUtil.currentDate());
        view.hideEndDate();

        view.setAccounts(getAccountsFunction.apply());

        view.setAccountingIntervals(intervalAdapter);
        view.setAccountingInterval(AccountingInterval.once.name());

        view.setAccountingTypes(typeAdapter);
        view.setAccountingType(AccountingType.outgoing.name());

        view.setAccountingCategories(categoryAdapter);
        reloadCategories();

        if(accountingId != 0) {
            loadAccountingProperties();
        }
    }

    private void loadAccountingProperties() {
        AccountingCursor accountingCursor = accountingDb.getById(accountingId);
        accountingCursor.moveToNext();
        view.setAccount(accountingCursor.getAccountName());
        String accountingIntervalInitial = accountingCursor.getInterval();
        view.setAccountingInterval(accountingIntervalInitial);
        view.setAccountingType(accountingCursor.getType());
        view.setDate(QuAccDateUtil.toString(accountingCursor.getDate()));
        view.setValue(parseValueFromIntegerFunction.apply(accountingCursor.getValue()));
        view.setComment(accountingCursor.getComment());
        accountingCursor.close();
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
    protected void onConfirm() {
        createOrUpdateAccountingFeature.apply(accountingId, accountingIntervalInitial, view);
    }

    @CheckedChange(R.id.endDateCheck)
    public void onToggleEndDate() {
        if(view.isEndDateActive()) {
            view.enableEndDate();
        } else {
            view.disableEndDate();
        }
    }

    @ItemSelect(R.id.interval)
    public void onIntervalSelection(boolean selected, int position) {
        if(view.getAccountingInterval().equals(AccountingInterval.once.name())) {
            view.hideEndDate();
        } else {
            view.showEndDate();
        }
        reloadCategories();
    }

    @ItemSelect(R.id.category)
    public void onCategorySelection(boolean selected, int position) {
        CategoryCursor item = view.getAccountingCategory();
        view.setSection(item.getSection());
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
