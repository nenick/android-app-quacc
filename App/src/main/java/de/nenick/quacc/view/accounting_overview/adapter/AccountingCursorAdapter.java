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

import de.nenick.quacc.accounting.GetAccountingByGroupFunction;
import de.nenick.quacc.accounting.GetAccountingByGroupFunction_;
import de.nenick.quacc.accounting.GetGroupsFunction;
import de.nenick.quacc.accounting.GetGroupsFunction_;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountingType;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

@EBean
public class AccountingCursorAdapter extends CursorTreeAdapter implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bean
    GetAccountingByGroupFunction getAccountingByGroupFunction;

    @Bean
    GetGroupsFunction getGroupsFunction;

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

    public AccountingCursorAdapter(Context context) {
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
        AccountingCursor accountingCursor = (AccountingCursor) groupCursor;
        long categoryId = accountingCursor.getCategoryId();
        String type = accountingCursor.getType();

        Date minDate = accountingCursor.getDateOrNull("minDate");
        GroupData childGroupData = new GroupData(groupPosition, categoryId, type, new DateTime(minDate), new DateTime(accountingCursor.getDate()));
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
        return AccountingGroupItemView_.build(context);
    }

    @Override
    protected void bindGroupView(View view, Context context, Cursor cursor, boolean b) {
        AccountingGroupItemView accountingView = (AccountingGroupItemView) view;
        AccountingCursor accountingCursor = (AccountingCursor) cursor;

        Date minDate = accountingCursor.getDateOrNull("minDate");
        accountingView.setDate(QuAccDateUtil.toString(minDate));
        accountingView.setEndDate(QuAccDateUtil.toString(accountingCursor.getDate()));

        accountingView.setCategory(accountingCursor.getCategoryName());
        accountingView.setValue(String.valueOf(accountingCursor.getValue()));

        AccountingType accountingType = AccountingType.valueOf(accountingCursor.getType());
        switch (accountingType) {
            case incoming:
                accountingView.showAsIncome();
                break;
            case outgoing:
                accountingView.showAsOutgoing();
        }
    }

    @Override
    protected View newChildView(Context context, Cursor cursor, boolean b, ViewGroup viewGroup) {
        return AccountingChildItemView_.build(context);
    }

    @Override
    protected void bindChildView(View view, Context context, Cursor cursor, boolean b) {
        AccountingChildItemView accountingView = (AccountingChildItemView) view;
        AccountingCursor accountingCursor = (AccountingCursor) cursor;

        accountingView.setDate(QuAccDateUtil.toString(accountingCursor.getDate()));
        accountingView.setInterval(accountingCursor.getInterval());
        accountingView.setCategory(accountingCursor.getCategoryName());
        accountingView.setComment(accountingCursor.getComment());
        accountingView.setValue(String.valueOf(accountingCursor.getValue()));

        AccountingType accountingType = AccountingType.valueOf(accountingCursor.getType());
        switch (accountingType) {
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
        return id <= 0;
    }
}
