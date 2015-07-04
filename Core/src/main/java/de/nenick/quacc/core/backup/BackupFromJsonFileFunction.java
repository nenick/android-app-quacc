package de.nenick.quacc.core.backup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import de.nenick.quacc.core.backup.model.AccountJson;
import de.nenick.quacc.core.backup.model.AccountingJson;
import de.nenick.quacc.core.backup.model.BackupJson;
import de.nenick.quacc.core.backup.model.CategoryJson;
import de.nenick.quacc.core.backup.model.IntervalJson;
import de.nenick.quacc.core.backup.model.TemplateJson;
import de.nenick.quacc.core.backup.model.TemplateMatchingJson;
import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.accounting.AccountingDb;
import de.nenick.quacc.database.category.CategoryDb;
import de.nenick.quacc.database.interval.IntervalDb;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.template.AccountingTemplateDb;
import de.nenick.quacc.database.template.TemplateMatchingDb;

@EBean
public class BackupFromJsonFileFunction {

    @RootContext
    Context context;

    @Bean
    GetInputStreamForFileFunction getInputStreamForFileFunction;

    @Bean
    AccountDb accountDb;

    @Bean
    CategoryDb categoryDb;

    @Bean
    AccountingDb accountingDb;

    @Bean
    IntervalDb intervalDb;

    @Bean
    AccountingTemplateDb accountingTemplateDb;

    @Bean
    TemplateMatchingDb templateMatchingDb;

    public void apply(String sourceLocation) {
        BackupJson backupJson = readBackupFile(sourceLocation);

        SQLiteDatabase writableDatabase = getDatabase();
        writableDatabase.beginTransaction();
        HashMap<Long, Long> accountsIdOriginalToCurrent = backupAccounts(backupJson);
        HashMap<Long, Long> categoriesIdOriginalToCurrent = backupCategories(backupJson);
        HashMap<Long, Long> accountingIdOriginalToCurrent = backupAccounting(backupJson, accountsIdOriginalToCurrent, categoriesIdOriginalToCurrent);
        HashMap<Long, Long> intervalsIdOriginalToCurrent = backupInterval(backupJson, accountsIdOriginalToCurrent, categoriesIdOriginalToCurrent);
        HashMap<Long, Long> templatesIdOriginalToCurrent = backupTemplates(backupJson, accountsIdOriginalToCurrent, categoriesIdOriginalToCurrent);
        backupTemplateMatches(backupJson, templatesIdOriginalToCurrent);
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();

        context.getContentResolver().notifyChange(getContentUri(), null);
    }

    protected Uri getContentUri() {
        return AccountingColumns.CONTENT_URI;
    }

    protected SQLiteDatabase getDatabase() {
        return QuAccSQLiteOpenHelper.getInstance(context).getWritableDatabase();
    }

    private HashMap<Long, Long> backupAccounts(BackupJson backupJson) {
        accountDb.deleteAll();
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (AccountJson entry : backupJson.accounts) {
            long id = accountDb.insert(entry.name, entry.initialValue);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private HashMap<Long, Long> backupCategories(BackupJson backupJson) {
        categoryDb.deleteAll();
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (CategoryJson entry : backupJson.categories) {
            long id = categoryDb.insert(entry.section, entry.name, entry.interval, entry.type, entry.level);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private HashMap<Long, Long> backupAccounting(BackupJson backupJson, HashMap<Long, Long> accountsIdOriginalToCurrent, HashMap<Long, Long> categoriesIdOriginalToCurrent) {
        accountingDb.deleteAll();
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (AccountingJson entry : backupJson.accounting) {
            long accountId = accountsIdOriginalToCurrent.get(entry.accountId);
            long categoryId = categoriesIdOriginalToCurrent.get(entry.categoryId);
            long id = accountingDb.insert(accountId, entry.type, entry.interval, categoryId, entry.date, entry.comment, entry.value);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private HashMap<Long, Long> backupInterval(BackupJson backupJson, HashMap<Long, Long> accountsIdOriginalToCurrent, HashMap<Long, Long> categoriesIdOriginalToCurrent) {
        intervalDb.deleteAll();
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (IntervalJson entry : backupJson.intervals) {
            long accountId = accountsIdOriginalToCurrent.get(entry.accountId);
            long categoryId = categoriesIdOriginalToCurrent.get(entry.categoryId);
            long id = intervalDb.insert(accountId, entry.type, entry.interval, categoryId, entry.dateStart, entry.dateEnd, entry.comment, entry.value);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private HashMap<Long, Long> backupTemplates(BackupJson backupJson, HashMap<Long, Long> accountsIdOriginalToCurrent, HashMap<Long, Long> categoriesIdOriginalToCurrent) {
        accountingTemplateDb.deleteAll();
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (TemplateJson entry : backupJson.templates) {
            long accountId = accountsIdOriginalToCurrent.get(entry.accountId);
            long categoryId = categoriesIdOriginalToCurrent.get(entry.categoryId);
            long id = accountingTemplateDb.insert(accountId, entry.type, entry.interval, categoryId, entry.comment);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private void backupTemplateMatches(BackupJson backupJson, HashMap<Long, Long> templatesIdOriginalToCurrent) {
        templateMatchingDb.deleteAll();
        for (TemplateMatchingJson entry : backupJson.templateMatches) {
            long templateId = templatesIdOriginalToCurrent.get(entry.templateId);
            templateMatchingDb.insert(entry.text, templateId);
        }
    }

    private BackupJson readBackupFile(String path) {
        InputStream backupFileStream = getInputStreamForFileFunction.apply(path);
        ObjectMapper mapper = new ObjectMapper();
        BackupJson backupJson;
        try {
            backupJson = mapper.readValue(backupFileStream, BackupJson.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return backupJson;
    }
}
