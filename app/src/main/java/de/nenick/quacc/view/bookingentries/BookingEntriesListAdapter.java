package de.nenick.quacc.view.bookingentries;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import java.util.Date;

import de.nenick.expandablerecyclerview.ExpandableCursorTreeAdapter;
import de.nenick.quacc.core.bookingentry.creation.CreateAccountingFromIntervalsFunction;
import de.nenick.quacc.database.LoaderCallback;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecCategoryEntriesByRange;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecCategorySummeryByRange;
import de.nenick.quacc.database.bookingentry.CategorySummeryCursor;
import de.nenick.quacc.database.bookinginterval.BookingIntervalRepository;
import de.nenick.quacc.database.bookinginterval.BookingIntervalSpecAll;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;

@EBean
class BookingEntriesListAdapter extends ExpandableCursorTreeAdapter<CategorySummeryListItem, BookingEntryListItem> {

    /*
        @Bean
        GetAccountingByGroupFunction getAccountingByGroupFunction;

        @Bean
        GetGroupsFunction getGroupsFunction;

        @Bean
        AccountingIntervalTranslator accountingIntervalTranslator;

        @Bean
        ParseValueFromIntegerFunction parseValueFromIntegerFunction;
    */
    @Bean
    AccountRepository accountRepository;

    @Bean
    BookingEntryRepository bookingEntryRepository;

    @Bean
    BookingIntervalRepository bookingIntervalRepository;

    @Bean
    protected CreateAccountingFromIntervalsFunction createAccountingFromIntervalsFunction;

    private long account;

    public BookingEntriesListAdapter(Context context) {
        super(context);
    }

    public void update(final long account, DateTime startDate, final DateTime endDate) {
        this.account = account;

        bookingIntervalRepository.loader(433, new BookingIntervalSpecAll(), new LoaderCallback<BookingIntervalCursor>() {
            @Override
            public void onLoadFinished(@Nullable BookingIntervalCursor cursor) {
                createAccountingFromIntervalsFunction.apply(account, endDate);
            }
        });

        bookingEntryRepository.loader(432, new BookingEntrySpecCategorySummeryByRange(account, startDate.toDate(), endDate.toDate()), new LoaderCallback<BookingEntryCursor>() {
            @Override
            public void onLoadFinished(@Nullable BookingEntryCursor cursor) {
                setGroupCursor(BookingEntrySpecCategorySummeryByRange.wrap(cursor));
            }
        });
    }

    @Override
    protected Cursor getChildrenCursor(Cursor groupCursor) {
        final int groupPosition = groupCursor.getPosition();
        CategorySummeryCursor categorySummeryCursor = BookingEntrySpecCategorySummeryByRange.wrap(groupCursor);
        long categoryId = categorySummeryCursor.getCategoryId();
        String type = categorySummeryCursor.getDirection();
        Date startDate = categorySummeryCursor.getDateStart();
        Date endDate = categorySummeryCursor.getDateEnd();

        BookingEntrySpecCategoryEntriesByRange specification = new BookingEntrySpecCategoryEntriesByRange(account, startDate, endDate, categoryId, type);
        bookingEntryRepository.loader(1000 + (int) categorySummeryCursor.getId(), specification, new LoaderCallback<BookingEntryCursor>() {
            @Override
            public void onLoadFinished(@Nullable BookingEntryCursor cursor) {
                Log.e("grouppos", "" + groupPosition);
                setChildrenCursor(groupPosition, cursor);
            }
        });
        return null;
    }

    @Override
    public CategorySummeryListItem onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return CategorySummeryListItem.create(parent.getContext());
    }

    @Override
    public BookingEntryListItem onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return BookingEntryListItem.create(parent.getContext());
    }
}
