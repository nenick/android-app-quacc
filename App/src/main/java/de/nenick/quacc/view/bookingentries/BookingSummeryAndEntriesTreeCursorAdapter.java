package de.nenick.quacc.view.bookingentries;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorTreeAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashMap;

import de.nenick.quacc.core.bookingentry.content.GetAccountingByGroupFunction;
import de.nenick.quacc.core.bookingentry.content.GetGroupsFunction;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.core.i18n.AccountingIntervalTranslator;
import de.nenick.quacc.database.LoaderCallback;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecCategoryEntriesByRange;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecCategorySummeryByRange;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.view.accounting_overview.adapter.AccountingTreeChildItemView;
import de.nenick.quacc.view.accounting_overview.adapter.AccountingTreeChildItemView_;
import de.nenick.quacc.view.accounting_overview.adapter.AccountingTreeGroupItemView;
import de.nenick.quacc.view.accounting_overview.adapter.AccountingTreeGroupItemView_;

@EBean
class BookingSummeryAndEntriesTreeCursorAdapter extends CursorTreeAdapter {

    @Bean
    GetAccountingByGroupFunction getAccountingByGroupFunction;

    @Bean
    GetGroupsFunction getGroupsFunction;

    @Bean
    AccountingIntervalTranslator accountingIntervalTranslator;

    @Bean
    ParseValueFromIntegerFunction parseValueFromIntegerFunction;

    @Bean
    AccountRepository accountRepository;

    @Bean
    BookingEntryRepository bookingEntryRepository;

    protected HashMap<Integer, GroupData> mGroupMap = new HashMap<>();
    private String account;

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

    public BookingSummeryAndEntriesTreeCursorAdapter(Context context) {
        super(null, context, false);
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void update(DateTime startDate, DateTime endDate) {
        GroupData groupData = new GroupData(-1, -1, null, startDate, endDate);
        int id = -groupData.hashCode();
        mGroupMap.put(id, groupData);

        AccountCursor accountCursor = accountRepository.query(new AccountSpecByName(account));
        accountCursor.moveToNext();
        long accountId = accountCursor.getId();
        accountCursor.close();

        bookingEntryRepository.loader(id, new BookingEntrySpecCategorySummeryByRange(accountId, startDate.toDate(), endDate.toDate()), new LoaderCallback<BookingEntryCursor>() {
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

        AccountCursor accountCursor = accountRepository.query(new AccountSpecByName(account));
        accountCursor.moveToNext();
        long accountId = accountCursor.getId();
        accountCursor.close();
        GroupData groupData = mGroupMap.get(groupId);
        bookingEntryRepository.loader(groupId, new BookingEntrySpecCategoryEntriesByRange(accountId, groupData.startDate.toDate(), groupData.endDate.toDate(), categoryId, type), new LoaderCallback<BookingEntryCursor>() {
            @Override
            public void onLoadFinished(BookingEntryCursor data) {
                setChildrenCursor(groupPosition, data);
            }
        });
        return null;
    }

    @Override
    protected View newGroupView(Context context, Cursor cursor, boolean b, ViewGroup viewGroup) {
        throw new UnsupportedOperationException("Should not be called.");
    }

    @Override
    protected void bindGroupView(View view, Context context, Cursor cursor, boolean b) {
        throw new UnsupportedOperationException("Should not be called.");
    }

    @Override
    protected View newChildView(Context context, Cursor cursor, boolean b, ViewGroup viewGroup) {
        throw new UnsupportedOperationException("Should not be called.");
    }

    @Override
    protected void bindChildView(View view, Context context, Cursor cursor, boolean b) {
        throw new UnsupportedOperationException("Should not be called.");
    }
}
