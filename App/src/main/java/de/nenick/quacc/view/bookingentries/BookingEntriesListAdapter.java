package de.nenick.quacc.view.bookingentries;

import android.content.Context;
import android.database.Cursor;
import android.view.ViewGroup;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashMap;

import de.nenick.expandablerecyclerview.ExpandableCursorTreeAdapter;
import de.nenick.quacc.database.LoaderCallback;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecCategoryEntriesByRange;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecCategorySummeryByRange;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;

@EBean
class BookingEntriesListAdapter extends ExpandableCursorTreeAdapter<ListItemCategorySummery, ListItemBookingEntry> {

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

    protected HashMap<Integer, GroupData> mGroupMap = new HashMap<>();
    private long account;

    private static class GroupData {

        public final int groupPosition;
        public final long categoryId;
        public final String type;
        public final DateTime startDate;
        public final DateTime endDate;

        public GroupData(int groupPosition, long categoryId, String type, DateTime startDate, DateTime endDate) {
            this.groupPosition = groupPosition;
            this.categoryId = categoryId;
            this.type = type;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    public BookingEntriesListAdapter(Context context) {
        super(context);
    }

    public void update(long account, DateTime startDate, DateTime endDate) {
        this.account = account;
        GroupData groupData = new GroupData(-1, -1, null, startDate, endDate);
        int id = -groupData.hashCode();
        mGroupMap.put(id, groupData);

        bookingEntryRepository.loader(id, new BookingEntrySpecCategorySummeryByRange(account, startDate.toDate(), endDate.toDate()), new LoaderCallback<BookingEntryCursor>() {
            @Override
            public void onLoadFinished(BookingEntryCursor data) {
                setGroupCursor(data);
            }
        });
    }

    @Override
    protected Cursor getChildrenCursor(Cursor groupCursor) {
        final int groupPosition = groupCursor.getPosition();
        BookingEntryCursor bookingEntryCursor = (BookingEntryCursor) groupCursor;
        long categoryId = bookingEntryCursor.getCategoryId();
        String type = bookingEntryCursor.getDirection();

        Date minDate = bookingEntryCursor.getDateOrNull("minDate");
        GroupData childGroupData = new GroupData(groupPosition, categoryId, type, new DateTime(minDate), new DateTime(bookingEntryCursor.getDate()));
        int groupId = childGroupData.hashCode();
        mGroupMap.put(groupId, childGroupData);

        GroupData groupData = mGroupMap.get(groupId);
        bookingEntryRepository.loader(groupId, new BookingEntrySpecCategoryEntriesByRange(account, groupData.startDate.toDate(), groupData.endDate.toDate(), categoryId, type), new LoaderCallback<BookingEntryCursor>() {
            @Override
            public void onLoadFinished(BookingEntryCursor data) {
                setChildrenCursor(groupPosition, data);
            }
        });
        return null;
    }

    @Override
    public ListItemCategorySummery onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return ListItemCategorySummery_.getInstance_(parent.getContext());
    }

    @Override
    public ListItemBookingEntry onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return ListItemBookingEntry_.getInstance_(parent.getContext());
    }
}
