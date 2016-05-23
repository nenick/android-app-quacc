package de.nenick.quacc.view.accounting_overview.adapter;

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
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.tools.AmountParser;

@EBean
public class AccountingTreeAdapter extends CursorTreeAdapter implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bean
    GetAccountingByGroupFunction getAccountingByGroupFunction;

    @Bean
    GetGroupsFunction getGroupsFunction;

    @Bean
    AccountingIntervalTranslator accountingIntervalTranslator;

    @Bean
    AmountParser amountParser;

    protected HashMap<Integer, GroupData> mGroupMap = new HashMap<>();
    private Activity context;
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

    public AccountingTreeAdapter(Context context) {
        super(null, context);
        this.context = (Activity)context;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void changeFor(DateTime startDate, DateTime endDate) {
        GroupData groupData = new GroupData(-1, -1, null, startDate, endDate);
        int id = -groupData.hashCode();
        mGroupMap.put(id, groupData);

        Loader<Cursor> loader = context.getLoaderManager().getLoader(id);
        if (loader != null && !loader.isReset()) {
            context.getLoaderManager().restartLoader(id, null, this);
        } else {
            context.getLoaderManager().initLoader(id, null, this);
        }
    }

    @Override
    protected Cursor getChildrenCursor(Cursor groupCursor) {
        int groupPosition = groupCursor.getPosition();
        BookingEntryCursor bookingEntryCursor = (BookingEntryCursor) groupCursor;
        long categoryId = bookingEntryCursor.getCategoryId();
        String type = bookingEntryCursor.getDirection();

        Date minDate = bookingEntryCursor.getDateOrNull("minDate");
        GroupData childGroupData = new GroupData(groupPosition, categoryId, type, new DateTime(minDate), new DateTime(bookingEntryCursor.getDate()));
        int groupId = childGroupData.hashCode();
        mGroupMap.put(groupId, childGroupData);

        Loader<Cursor> loader = context.getLoaderManager().getLoader(groupId);
        if (loader != null && !loader.isReset()) {
            context.getLoaderManager().restartLoader(groupId, null, this);
        } else {
            context.getLoaderManager().initLoader(groupId, null, this);
        }

        return null;
    }

    @Override
    protected View newGroupView(Context context, Cursor cursor, boolean b, ViewGroup viewGroup) {
        return AccountingTreeGroupItemView_.build(context);
    }

    @Override
    protected void bindGroupView(View view, Context context, Cursor cursor, boolean b) {
        AccountingTreeGroupItemView accountingView = (AccountingTreeGroupItemView) view;
        BookingEntryCursor bookingEntryCursor = (BookingEntryCursor) cursor;

        Date minDate = bookingEntryCursor.getDateOrNull("minDate");
        accountingView.setDate(QuAccDateUtil.toString(minDate));
        accountingView.setEndDate(QuAccDateUtil.toString(bookingEntryCursor.getDate()));

        accountingView.setCategory(bookingEntryCursor.getCategoryName());
        accountingView.setValue(amountParser.asString(bookingEntryCursor.getAmount()));

        BookingDirectionOption bookingDirectionOption = BookingDirectionOption.valueOf(bookingEntryCursor.getDirection());
        switch (bookingDirectionOption) {
            case incoming:
                accountingView.showAsIncome();
                break;
            case outgoing:
                accountingView.showAsOutgoing();
        }
    }

    @Override
    protected View newChildView(Context context, Cursor cursor, boolean b, ViewGroup viewGroup) {
        return AccountingTreeChildItemView_.build(context);
    }

    @Override
    protected void bindChildView(View view, Context context, Cursor cursor, boolean b) {
        AccountingTreeChildItemView accountingView = (AccountingTreeChildItemView) view;
        BookingEntryCursor bookingEntryCursor = (BookingEntryCursor) cursor;

        accountingView.setDate(QuAccDateUtil.toString(bookingEntryCursor.getDate()));
        accountingView.setInterval(accountingIntervalTranslator.translate(bookingEntryCursor.getInterval()));
        accountingView.setCategory(bookingEntryCursor.getCategoryName());
        accountingView.setComment(bookingEntryCursor.getComment());
        accountingView.setValue(amountParser.asString(bookingEntryCursor.getAmount()));

        BookingDirectionOption bookingDirectionOption = BookingDirectionOption.valueOf(bookingEntryCursor.getDirection());
        switch (bookingDirectionOption) {
            case incoming:
                accountingView.showAsIncome();
                break;
            case outgoing:
                accountingView.showAsOutgoing();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, Bundle bundle) {
        if (isGroupCursor(id)) {
            // group cursor
            return new CursorLoader(context) {
                @Override
                public Cursor loadInBackground() {
                    GroupData groupData = mGroupMap.get(id);
                    return getGroupsFunction.apply(account, groupData.startDate, groupData.endDate);
                }
            };
        } else {
            // child cursor
            return new CursorLoader(context) {
                @Override
                public Cursor loadInBackground() {
                    GroupData groupData = mGroupMap.get(id);
                    return getAccountingByGroupFunction.apply(account, groupData.categoryId, groupData.type, groupData.startDate, groupData.endDate);
                }
            };
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        int id = loader.getId();
        if (id < 0) {
            setGroupCursor(cursor);
        } else {
            int groupPos = mGroupMap.get(id).groupPosition;
            setChildrenCursor(groupPos, cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        int id = loader.getId();
        if (isGroupCursor(id)) {
            setGroupCursor(null);
        } else {
            setChildrenCursor(mGroupMap.get(id).groupPosition, null);
        }
    }

    private boolean isGroupCursor(int id) {
        return id < 0;
    }
}
