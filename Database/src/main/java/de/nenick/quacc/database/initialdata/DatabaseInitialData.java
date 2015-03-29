package de.nenick.quacc.database.initialdata;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryColumns;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryContentValues;
import de.nenick.quacc.database.provider.base.AbstractContentValues;

public class DatabaseInitialData {
    public void insert(SQLiteDatabase database) {
        addAccountData(database);
        addAccountingCategoryData(database);
    }

    private void addAccountingCategoryData(SQLiteDatabase database) {
        List<AbstractContentValues> data = new ArrayList<>();
        data.add(new AccountingCategoryContentValues().putName("Beruf"));
        data.add(new AccountingCategoryContentValues().putName("Essen"));
        data.add(new AccountingCategoryContentValues().putName("Freizeit"));
        data.add(new AccountingCategoryContentValues().putName("Miete"));
        insert(database, AccountingCategoryColumns.TABLE_NAME, data);
    }

    private void addAccountData(SQLiteDatabase database) {
        List<AbstractContentValues> data = new ArrayList<>();
        data.add(new AccountContentValues().putName("Konto").putDescription("Hauptkonto worüber fast alles läuft"));
        data.add(new AccountContentValues().putName("Sparkonto").putDescription("Separates Konto zum sparen"));
        data.add(new AccountContentValues().putName("Bar").putDescription("Alltägliche Geld"));
        insert(database, AccountColumns.TABLE_NAME, data);
    }

    private void insert(SQLiteDatabase database, String tableName, List<AbstractContentValues> entries) {
        for (AbstractContentValues entry : entries) {
            database.insert(tableName, null, entry.values());
        }

    }
}
