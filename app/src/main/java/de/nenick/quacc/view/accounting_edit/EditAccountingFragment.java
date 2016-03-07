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
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecById;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.toolscollection.amountparser.AmountParser;
import de.nenick.toolscollection.amountparser.ParseValueFromIntegerFunction;
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
    long bookingEntryId;

    String accountingIntervalInitial;

    @Bean
    EditAccountingView view;

    @Bean
    GetAccountsFunction getAccountsFunction;

    @Bean
    SpeechRecognitionFeature speechRecognitionFeature;

    @Bean
    IntervalAdapter intervalAdapter;

    @Bean
    TypeAdapter typeAdapter;

    @Bean
    CategoryAdapter categoryAdapter;

    @Bean
    BookingEntryRepository bookingEntryRepository;

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
        view.setAccountingInterval(BookingIntervalOption.once.name());

        view.setAccountingTypes(typeAdapter);
        view.setAccountingType(BookingDirectionOption.outgoing.name());

        view.setAccountingCategories(categoryAdapter);
        reloadCategories();

        if(bookingEntryId != 0) {
            loadBookingEntryProperties();
        }
    }

    private void loadBookingEntryProperties() {
        BookingEntryCursor accountingCursor = bookingEntryRepository.query(new BookingEntrySpecById(bookingEntryId));
        accountingCursor.moveToNext();
        view.setAccount(accountingCursor.getAccountName());
        String accountingIntervalInitial = accountingCursor.getInterval();
        view.setAccountingInterval(accountingIntervalInitial);
        view.setAccountingType(accountingCursor.getDirection());
        view.setDate(QuAccDateUtil.toString(accountingCursor.getDate()));
        view.setValue(AmountParser.asString(accountingCursor.getAmount()));
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
        createOrUpdateAccountingFeature.apply(bookingEntryId, accountingIntervalInitial, view);
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
        if(view.getAccountingInterval().equals(BookingIntervalOption.once.name())) {
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

    @ItemSelect(R.id.direction)
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
