package de.nenick.quacc.core.backup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import de.nenick.quacc.core.backup.model.AccountJson;
import de.nenick.quacc.core.backup.model.BookingEntryJson;
import de.nenick.quacc.core.backup.model.BackupJson;
import de.nenick.quacc.core.backup.model.CategoryJson;
import de.nenick.quacc.core.backup.model.BookingIntervalJson;
import de.nenick.quacc.core.backup.model.BookingTemplateJson;
import de.nenick.quacc.core.backup.model.TemplateMatchingJson;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecAll;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecAll;
import de.nenick.quacc.database.bookinginterval.BookingIntervalRepository;
import de.nenick.quacc.database.bookinginterval.BookingIntervalSpecAll;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateRepository;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateSpecAll;
import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordRepository;
import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordSpecAll;
import de.nenick.quacc.database.category.CategoryRepository;
import de.nenick.quacc.database.category.CategorySpecAll;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateContentValues;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordContentValues;
import de.nenick.quacc.database.provider.category.CategoryContentValues;

@EBean
public class BackupFromJsonFileFunction {

    @RootContext
    Context context;

    @Bean
    GetInputStreamForFileFunction getInputStreamForFileFunction;

    @Bean
    AccountRepository accountRepository;

    @Bean
    CategoryRepository categoryRepository;

    @Bean
    BookingEntryRepository bookingEntryRepository;

    @Bean
    BookingIntervalRepository bookingIntervalRepository;

    @Bean
    BookingTemplateRepository bookingTemplateRepository;

    @Bean
    BookingTemplateKeywordRepository bookingTemplateKeywordRepository;

    public void apply(String sourceLocation) {
        BackupJson backupJson = readBackupFile(sourceLocation);

        SQLiteDatabase writableDatabase = getDatabase();
        writableDatabase.beginTransaction();
        HashMap<Long, Long> accountsIdOriginalToCurrent = backupAccounts(backupJson);
        HashMap<Long, Long> categoriesIdOriginalToCurrent = backupCategories(backupJson);
        HashMap<Long, Long> accountingIdOriginalToCurrent = backupBookingEntries(backupJson, accountsIdOriginalToCurrent, categoriesIdOriginalToCurrent);
        HashMap<Long, Long> intervalsIdOriginalToCurrent = backupBookingIntervals(backupJson, accountsIdOriginalToCurrent, categoriesIdOriginalToCurrent);
        HashMap<Long, Long> templatesIdOriginalToCurrent = backupBookingTemplates(backupJson, accountsIdOriginalToCurrent, categoriesIdOriginalToCurrent);
        backupBookingTemplateKeywords(backupJson, templatesIdOriginalToCurrent);
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
    }

    protected SQLiteDatabase getDatabase() {
        return QuAccSQLiteOpenHelper.getInstance(context).getWritableDatabase();
    }

    private HashMap<Long, Long> backupAccounts(BackupJson backupJson) {
        accountRepository.delete(new AccountSpecAll());
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (AccountJson entry : backupJson.accounts) {
            AccountContentValues account = new AccountContentValues()
                    .putName(entry.name)
                    .putInitialvalue(entry.initialValue);
            long id = accountRepository.insert(account);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private HashMap<Long, Long> backupCategories(BackupJson backupJson) {
        categoryRepository.delete(new CategorySpecAll());
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (CategoryJson entry : backupJson.categories) {

            CategoryContentValues values = new CategoryContentValues()
                    .putSection(entry.section)
                    .putName(entry.name)
                    .putInterval(entry.interval)
                    .putDirection(entry.direction)
                    .putLevel(entry.level);

            long id = categoryRepository.insert(values);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private HashMap<Long, Long> backupBookingEntries(BackupJson backupJson, HashMap<Long, Long> accountsIdOriginalToCurrent, HashMap<Long, Long> categoriesIdOriginalToCurrent) {
        bookingEntryRepository.delete(new BookingEntrySpecAll());
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (BookingEntryJson entry : backupJson.bookingEntries) {
            long accountId = accountsIdOriginalToCurrent.get(entry.accountId);
            long categoryId = categoriesIdOriginalToCurrent.get(entry.categoryId);
            BookingEntryContentValues values = new BookingEntryContentValues()
                    .putAccountId(accountId)
                    .putCategoryId(categoryId)
                    .putDirection(entry.direction)
                    .putInterval(entry.interval)
                    .putDate(entry.date)
                    .putComment(entry.comment)
                    .putAmount(entry.amount);
            long id = bookingEntryRepository.insert(values);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private HashMap<Long, Long> backupBookingIntervals(BackupJson backupJson, HashMap<Long, Long> accountsIdOriginalToCurrent, HashMap<Long, Long> categoriesIdOriginalToCurrent) {
        bookingIntervalRepository.delete(new BookingIntervalSpecAll());
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (BookingIntervalJson entry : backupJson.bookingIntervals) {
            long accountId = accountsIdOriginalToCurrent.get(entry.accountId);
            long categoryId = categoriesIdOriginalToCurrent.get(entry.categoryId);
            BookingIntervalContentValues values = new BookingIntervalContentValues()
                    .putAccountId(accountId)
                    .putCategoryId(categoryId)
                    .putDirection(entry.direction)
                    .putInterval(entry.interval)
                    .putDateStart(entry.dateStart)
                    .putDateEnd(entry.dateEnd)
                    .putComment(entry.comment)
                    .putAmount(entry.amount);
            long id = bookingIntervalRepository.insert(values);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private HashMap<Long, Long> backupBookingTemplates(BackupJson backupJson, HashMap<Long, Long> accountsIdOriginalToCurrent, HashMap<Long, Long> categoriesIdOriginalToCurrent) {
        bookingTemplateRepository.delete(new BookingTemplateSpecAll());
        HashMap<Long, Long> idOriginalToCurrent = new HashMap<>();
        for (BookingTemplateJson entry : backupJson.bookingTemplates) {
            long accountId = accountsIdOriginalToCurrent.get(entry.accountId);
            long categoryId = categoriesIdOriginalToCurrent.get(entry.categoryId);
            BookingTemplateContentValues values = new BookingTemplateContentValues()
                    .putAccountId(accountId)
                    .putCategoryId(categoryId)
                    .putDirection(entry.direction)
                    .putInterval(entry.interval)
                    .putComment(entry.comment);
            long id = bookingTemplateRepository.insert(values);
            idOriginalToCurrent.put(entry.id, id);
        }
        return idOriginalToCurrent;
    }

    private void backupBookingTemplateKeywords(BackupJson backupJson, HashMap<Long, Long> templatesIdOriginalToCurrent) {
        bookingTemplateKeywordRepository.delete(new BookingTemplateKeywordSpecAll());
        for (TemplateMatchingJson entry : backupJson.bookingTemplateKeywords) {
            long templateId = templatesIdOriginalToCurrent.get(entry.bookingTemplateId);
            BookingTemplateKeywordContentValues values = new BookingTemplateKeywordContentValues()
                    .putBookingTemplateId(templateId)
                    .putText(entry.text);
            bookingTemplateKeywordRepository.insert(values);
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
