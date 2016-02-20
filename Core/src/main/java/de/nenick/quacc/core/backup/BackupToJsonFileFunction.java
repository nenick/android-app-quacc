package de.nenick.quacc.core.backup;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

import de.nenick.quacc.core.backup.model.AccountJson;
import de.nenick.quacc.core.backup.model.BackupJson;
import de.nenick.quacc.core.backup.model.BookingEntryJson;
import de.nenick.quacc.core.backup.model.BookingIntervalJson;
import de.nenick.quacc.core.backup.model.BookingTemplateJson;
import de.nenick.quacc.core.backup.model.CategoryJson;
import de.nenick.quacc.core.backup.model.TemplateMatchingJson;
import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecAll;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecByInterval;
import de.nenick.quacc.database.bookinginterval.BookingIntervalRepository;
import de.nenick.quacc.database.bookinginterval.BookingIntervalSpecAll;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateRepository;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateSpecAll;
import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordRepository;
import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordSpecAll;
import de.nenick.quacc.database.category.CategoryRepository;
import de.nenick.quacc.database.category.CategorySpecAll;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordCursor;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class BackupToJsonFileFunction {

    @Bean
    GetOutputStreamToFileFunction getOutputStreamToFileFunction;

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

    public void apply(String path) {
        BackupJson backupJson = new BackupJson();

        backupAccounts(backupJson);
        backupCategories(backupJson);
        backupBookingEntries(backupJson);
        backupBookingIntervals(backupJson);
        backupBookingTemplates(backupJson);
        backupBookingTemplateKeywords(backupJson);


        writeBackupFile(path, backupJson);
    }

    private void backupAccounts(BackupJson backupJson) {
        AccountCursor all = accountRepository.query(new AccountSpecAll());
        while (all.moveToNext()) {
            backupJson.accounts.add(new AccountJson(all.getId(), all.getName(), all.getInitialvalue()));
        }
        all.close();
    }

    private void backupCategories(BackupJson backupJson) {
        CategoryCursor all = categoryRepository.query(new CategorySpecAll());
        while (all.moveToNext()) {
            backupJson.categories.add(new CategoryJson(all.getId(), all.getName(), all.getSection(), all.getInterval(), all.getDirection(), all.getLevel()));
        }
        all.close();
    }

    private void backupBookingEntries(BackupJson backupJson) {
        BookingEntryCursor all = bookingEntryRepository.query(new BookingEntrySpecByInterval(BookingIntervalOption.once.name()));
        while (all.moveToNext()) {
            backupJson.bookingEntries.add(new BookingEntryJson(all.getId(), all.getAccountId(), all.getCategoryId(), all.getComment(), all.getInterval(), all.getDirection(), all.getDate(), all.getAmount()));
        }
        all.close();
    }

    private void backupBookingIntervals(BackupJson backupJson) {
        BookingIntervalCursor all = bookingIntervalRepository.query(new BookingIntervalSpecAll());
        while (all.moveToNext()) {
            backupJson.bookingIntervals.add(new BookingIntervalJson(all.getId(), all.getAccountId(), all.getCategoryId(), all.getComment(), all.getInterval(), all.getDirection(), all.getDateStart(), all.getDateEnd(), all.getAmount()));
        }
        all.close();
    }

    private void backupBookingTemplates(BackupJson backupJson) {
        BookingTemplateCursor all = bookingTemplateRepository.query(new BookingTemplateSpecAll());
        while (all.moveToNext()) {
            backupJson.bookingTemplates.add(new BookingTemplateJson(all.getId(), all.getAccountId(), all.getCategoryId(), all.getComment(), all.getInterval(), all.getDirection()));
        }
        all.close();
    }

    private void backupBookingTemplateKeywords(BackupJson backupJson) {
        BookingTemplateKeywordCursor all = bookingTemplateKeywordRepository.query(new BookingTemplateKeywordSpecAll());
        while (all.moveToNext()) {
            backupJson.bookingTemplateKeywords.add(new TemplateMatchingJson(all.getText(), all.getBookingTemplateId()));
        }
        all.close();
    }

    private void writeBackupFile(String path, BackupJson backupJson) {
        OutputStream outputStream = getOutputStreamToFileFunction.apply(path);
        try {
            new ObjectMapper().writeValue(outputStream, backupJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
