package de.nenick.quacc.database.initialdata;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.account.AccountContentValues;

public class AccountInitialData {
    public void insert(SQLiteDatabase database) {
        addAccountData(database);
    }

    private void addAccountData(SQLiteDatabase database) {
        List<AccountContentValues> data = new ArrayList<>();
        data.add(new AccountContentValues().putName("Konto").putDescription("Hauptkonto worüber fast alles läuft"));
        data.add(new AccountContentValues().putName("Sparkonto").putDescription("Separates Konto zum sparen"));
        data.add(new AccountContentValues().putName("Bar").putDescription("Alltägliche Geld"));
        insert(database, AccountColumns.TABLE_NAME, data);
    }

    private void insert(SQLiteDatabase database, String tableName, List<AccountContentValues> entries) {
        for (AccountContentValues entry : entries) {
            database.insert(tableName, null, entry.values());
        }

    }
}
