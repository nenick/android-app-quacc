package de.nenick.quacc.core.backup;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
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
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.interval.IntervalCursor;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingCursor;
import de.nenick.quacc.database.template.AccountingTemplateDb;
import de.nenick.quacc.database.template.TemplateMatchingDb;

@EBean
public class BackupToJsonFileFunction {

    @Bean
    GetOutputStreamToFileFunction getOutputStreamToFileFunction;

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

    public void apply(String path) {
        BackupJson backupJson = new BackupJson();

        backupAccounts(backupJson);
        backupCategories(backupJson);
        backupAccounting(backupJson);
        backupInterval(backupJson);
        backupTemplates(backupJson);
        backupTemplateMatches(backupJson);


        writeBackupFile(path, backupJson);
    }

    private void backupAccounts(BackupJson backupJson) {
        AccountCursor all = accountDb.getAll();
        while (all.moveToNext()) {
            backupJson.accounts.add(new AccountJson(all.getId(), all.getName(), all.getInitialvalue()));
        }
        all.close();
    }

    private void backupCategories(BackupJson backupJson) {
        CategoryCursor all = categoryDb.getAll();
        while (all.moveToNext()) {
            backupJson.categories.add(new CategoryJson(all.getId(), all.getName(), all.getSection(), all.getInterval(), all.getType(), all.getLevel()));
        }
        all.close();
    }

    private void backupAccounting(BackupJson backupJson) {
        AccountingCursor all = accountingDb.getAllByInterval(AccountingInterval.once.name());
        while (all.moveToNext()) {
            backupJson.accounting.add(new AccountingJson(all.getId(), all.getAccountId(), all.getCategoryId(), all.getComment(), all.getInterval(), all.getType(), all.getDate(), all.getValue()));
        }
        all.close();
    }

    private void backupInterval(BackupJson backupJson) {
        IntervalCursor all = intervalDb.getAll();
        while (all.moveToNext()) {
            backupJson.intervals.add(new IntervalJson(all.getId(), all.getAccountId(), all.getCategoryId(), all.getComment(), all.getInterval(), all.getType(), all.getDateStart(), all.getDateEnd(), all.getValue()));
        }
        all.close();
    }

    private void backupTemplates(BackupJson backupJson) {
        AccountingTemplateCursor all = accountingTemplateDb.getAll();
        while (all.moveToNext()) {
            backupJson.templates.add(new TemplateJson(all.getId(), all.getAccountId(), all.getCategoryId(), all.getComment(), all.getInterval(), all.getType()));
        }
        all.close();
    }

    private void backupTemplateMatches(BackupJson backupJson) {
        TemplateMatchingCursor all = templateMatchingDb.getAll();
        while (all.moveToNext()) {
            backupJson.templateMatches.add(new TemplateMatchingJson(all.getText(), all.getAccountingTemplateId()));
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
