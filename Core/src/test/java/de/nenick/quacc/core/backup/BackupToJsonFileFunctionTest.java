package de.nenick.quacc.core.backup;


import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class BackupToJsonFileFunctionTest {

    @InjectMocks
    BackupToJsonFileFunction backupToJsonFileFunction;

    @Mock
    GetOutputStreamToFileFunction getOutputStreamToFileFunction;

    @Mock
    AccountDb accountDb;

    @Mock
    AccountCursor accountCursor;

    @Mock
    CategoryDb categoryDb;

    @Mock
    CategoryCursor categoryCursor;

    @Mock
    AccountingDb accountingDb;

    @Mock
    AccountingCursor accountingCursor;

    @Mock
    AccountingTemplateDb accountingTemplateDb;

    @Mock
    AccountingTemplateCursor accountingTemplateCursor;

    @Mock
    TemplateMatchingDb templateMatchingDb;

    @Mock
    TemplateMatchingCursor templateMatchingCursor;

    @Mock
    IntervalDb intervalDb;

    @Mock
    IntervalCursor intervalCursor;

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    String backLocation = "/my/backup/location";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        given(accountDb.getAll()).willReturn(accountCursor);
        given(categoryDb.getAll()).willReturn(categoryCursor);
        given(accountingDb.getAllByInterval(anyString())).willReturn(accountingCursor);
        given(accountingTemplateDb.getAll()).willReturn(accountingTemplateCursor);
        given(templateMatchingDb.getAll()).willReturn(templateMatchingCursor);
        given(intervalDb.getAll()).willReturn(intervalCursor);

        given(getOutputStreamToFileFunction.apply(anyString())).willReturn(outputStream);
    }

    @Test
    public void shouldWriteFile() {
        given(getOutputStreamToFileFunction.apply(anyString())).willReturn(outputStream);
        backupToJsonFileFunction.apply(backLocation);
        verify(getOutputStreamToFileFunction).apply(backLocation);
        String backupContent = new String(outputStream.toByteArray());
        assertThat(backupContent).isEqualTo("{\"accounts\":[],\"categories\":[],\"accounting\":[],\"templates\":[],\"templateMatches\":[],\"intervals\":[]}");
    }

    @Test
    public void shouldBackupAccounts() throws IOException {
        long accountId = 10;
        String accountName = "name";
        int accountInitialValue = 1;

        given(accountCursor.moveToNext()).willReturn(true).willReturn(false);
        given(accountCursor.getId()).willReturn(accountId);
        given(accountCursor.getName()).willReturn(accountName);
        given(accountCursor.getInitialvalue()).willReturn(accountInitialValue);

        backupToJsonFileFunction.apply(backLocation);

        String backupContent = new String(outputStream.toByteArray());
        BackupJson backupJson = new ObjectMapper().readValue(backupContent, BackupJson.class);

        assertThat(backupJson.accounts).hasSize(1);
        AccountJson json = backupJson.accounts.get(0);
        assertThat(json.id).isEqualTo(accountId);
        assertThat(json.name).isEqualTo(accountName);
        assertThat(json.initialValue).isEqualTo(accountInitialValue);
    }

    @Test
    public void shouldBackupCategories() throws IOException {
        long categoryId = 11;
        String categoryName = "name";
        String categorySection = "section";
        String categoryInterval = "interval";
        String categoryType = "type";
        int categoryLevel = 1;

        given(categoryCursor.moveToNext()).willReturn(true).willReturn(false);
        given(categoryCursor.getId()).willReturn(categoryId);
        given(categoryCursor.getName()).willReturn(categoryName);
        given(categoryCursor.getSection()).willReturn(categorySection);
        given(categoryCursor.getInterval()).willReturn(categoryInterval);
        given(categoryCursor.getType()).willReturn(categoryType);
        given(categoryCursor.getLevel()).willReturn(categoryLevel);

        backupToJsonFileFunction.apply(backLocation);

        String backupContent = new String(outputStream.toByteArray());
        BackupJson backupJson = new ObjectMapper().readValue(backupContent, BackupJson.class);

        assertThat(backupJson.categories).hasSize(1);
        CategoryJson json = backupJson.categories.get(0);
        assertThat(json.id).isEqualTo(categoryId);
        assertThat(json.name).isEqualTo(categoryName);
        assertThat(json.section).isEqualTo(categorySection);
        assertThat(json.interval).isEqualTo(categoryInterval);
        assertThat(json.type).isEqualTo(categoryType);
        assertThat(json.level).isEqualTo(categoryLevel);
    }

    @Test
    public void shouldBackupAccounting() throws IOException {
        long accountId = 10;
        String accountName = "name";
        int accountInitialValue = 1;

        given(accountCursor.moveToNext()).willReturn(true).willReturn(false);
        given(accountCursor.getId()).willReturn(accountId);
        given(accountCursor.getName()).willReturn(accountName);
        given(accountCursor.getInitialvalue()).willReturn(accountInitialValue);

        long categoryId = 11;
        String categoryName = "name";
        String categorySection = "section";
        String categoryInterval = "interval";
        String categoryType = "type";
        int categoryLevel = 1;

        given(categoryCursor.moveToNext()).willReturn(true).willReturn(false);
        given(categoryCursor.getId()).willReturn(categoryId);
        given(categoryCursor.getName()).willReturn(categoryName);
        given(categoryCursor.getSection()).willReturn(categorySection);
        given(categoryCursor.getInterval()).willReturn(categoryInterval);
        given(categoryCursor.getType()).willReturn(categoryType);
        given(categoryCursor.getLevel()).willReturn(categoryLevel);

        long accountingId = 12;
        String accountingInterval = "interval";
        String accountingType = "type";
        String accountingComment = "comment";
        Date accountingDate = new Date();
        int accountingValue = 1;

        given(accountingCursor.moveToNext()).willReturn(true).willReturn(false);
        given(accountingCursor.getId()).willReturn(accountingId);
        given(accountingCursor.getInterval()).willReturn(accountingInterval);
        given(accountingCursor.getType()).willReturn(accountingType);
        given(accountingCursor.getComment()).willReturn(accountingComment);
        given(accountingCursor.getDate()).willReturn(accountingDate);
        given(accountingCursor.getValue()).willReturn(accountingValue);

        backupToJsonFileFunction.apply(backLocation);

        String backupContent = new String(outputStream.toByteArray());
        BackupJson backupJson = new ObjectMapper().readValue(backupContent, BackupJson.class);

        assertThat(backupJson.accounting).hasSize(1);
        AccountingJson json = backupJson.accounting.get(0);
        assertThat(json.id).isEqualTo(accountingId);
        assertThat(json.interval).isEqualTo(accountingInterval);
        assertThat(json.type).isEqualTo(accountingType);
        assertThat(json.comment).isEqualTo(accountingComment);
        assertThat(json.date).isEqualTo(accountingDate);
        assertThat(json.value).isEqualTo(accountingValue);
    }

    @Test
    public void shouldBackupAccountingOnlyIfNotFromInterval() throws IOException {
        backupToJsonFileFunction.apply(backLocation);
        verify(accountingDb).getAllByInterval(AccountingInterval.once.name());
    }

    @Test
    public void shouldBackupIntervals() throws IOException {
        long accountId = 10;
        String accountName = "name";
        int accountInitialValue = 1;

        given(accountCursor.moveToNext()).willReturn(true).willReturn(false);
        given(accountCursor.getId()).willReturn(accountId);
        given(accountCursor.getName()).willReturn(accountName);
        given(accountCursor.getInitialvalue()).willReturn(accountInitialValue);

        long categoryId = 11;
        String categoryName = "name";
        String categorySection = "section";
        String categoryInterval = "interval";
        String categoryType = "type";
        int categoryLevel = 1;

        given(categoryCursor.moveToNext()).willReturn(true).willReturn(false);
        given(categoryCursor.getId()).willReturn(categoryId);
        given(categoryCursor.getName()).willReturn(categoryName);
        given(categoryCursor.getSection()).willReturn(categorySection);
        given(categoryCursor.getInterval()).willReturn(categoryInterval);
        given(categoryCursor.getType()).willReturn(categoryType);
        given(categoryCursor.getLevel()).willReturn(categoryLevel);

        long intervalId = 12;
        String intervalInterval = "interval";
        String intervalType = "type";
        String intervalComment = "comment";
        Date intervalDateStart = new Date(new Date().getTime() - 300000);
        Date intervalDateEnd = new Date();
        int intervalValue = 1;

        given(intervalCursor.moveToNext()).willReturn(true).willReturn(false);
        given(intervalCursor.getId()).willReturn(intervalId);
        given(intervalCursor.getInterval()).willReturn(intervalInterval);
        given(intervalCursor.getType()).willReturn(intervalType);
        given(intervalCursor.getComment()).willReturn(intervalComment);
        given(intervalCursor.getDateStart()).willReturn(intervalDateStart);
        given(intervalCursor.getDateEnd()).willReturn(intervalDateEnd);
        given(intervalCursor.getValue()).willReturn(intervalValue);

        backupToJsonFileFunction.apply(backLocation);

        String backupContent = new String(outputStream.toByteArray());
        BackupJson backupJson = new ObjectMapper().readValue(backupContent, BackupJson.class);

        assertThat(backupJson.intervals).hasSize(1);
        IntervalJson json = backupJson.intervals.get(0);
        assertThat(json.id).isEqualTo(intervalId);
        assertThat(json.interval).isEqualTo(intervalInterval);
        assertThat(json.type).isEqualTo(intervalType);
        assertThat(json.comment).isEqualTo(intervalComment);
        assertThat(json.dateStart).isEqualTo(intervalDateStart);
        assertThat(json.dateEnd).isEqualTo(intervalDateEnd);
        assertThat(json.value).isEqualTo(intervalValue);
    }

    @Test
    public void shouldBackupTemplates() throws IOException {
        long accountId = 10;
        String accountName = "name";
        int accountInitialValue = 1;

        given(accountCursor.moveToNext()).willReturn(true).willReturn(false);
        given(accountCursor.getId()).willReturn(accountId);
        given(accountCursor.getName()).willReturn(accountName);
        given(accountCursor.getInitialvalue()).willReturn(accountInitialValue);

        long categoryId = 11;
        String categoryName = "name";
        String categorySection = "section";
        String categoryInterval = "interval";
        String categoryType = "type";
        int categoryLevel = 1;

        given(categoryCursor.moveToNext()).willReturn(true).willReturn(false);
        given(categoryCursor.getId()).willReturn(categoryId);
        given(categoryCursor.getName()).willReturn(categoryName);
        given(categoryCursor.getSection()).willReturn(categorySection);
        given(categoryCursor.getInterval()).willReturn(categoryInterval);
        given(categoryCursor.getType()).willReturn(categoryType);
        given(categoryCursor.getLevel()).willReturn(categoryLevel);

        long templateId = 12;
        String templateInterval = "interval";
        String templateType = "type";
        String templateComment = "comment";

        given(accountingTemplateCursor.moveToNext()).willReturn(true).willReturn(false);
        given(accountingTemplateCursor.getId()).willReturn(templateId);
        given(accountingTemplateCursor.getInterval()).willReturn(templateInterval);
        given(accountingTemplateCursor.getType()).willReturn(templateType);
        given(accountingTemplateCursor.getComment()).willReturn(templateComment);

        backupToJsonFileFunction.apply(backLocation);

        String backupContent = new String(outputStream.toByteArray());
        BackupJson backupJson = new ObjectMapper().readValue(backupContent, BackupJson.class);

        assertThat(backupJson.templates).hasSize(1);
        TemplateJson json = backupJson.templates.get(0);
        assertThat(json.id).isEqualTo(templateId);
        assertThat(json.interval).isEqualTo(templateInterval);
        assertThat(json.type).isEqualTo(templateType);
        assertThat(json.comment).isEqualTo(templateComment);
    }

    @Test
    public void shouldBackupTemplateMatching() throws IOException {
        long accountId = 10;
        String accountName = "name";
        int accountInitialValue = 1;

        given(accountCursor.moveToNext()).willReturn(true).willReturn(false);
        given(accountCursor.getId()).willReturn(accountId);
        given(accountCursor.getName()).willReturn(accountName);
        given(accountCursor.getInitialvalue()).willReturn(accountInitialValue);

        long categoryId = 11;
        String categoryName = "name";
        String categorySection = "section";
        String categoryInterval = "interval";
        String categoryType = "type";
        int categoryLevel = 1;

        given(categoryCursor.moveToNext()).willReturn(true).willReturn(false);
        given(categoryCursor.getId()).willReturn(categoryId);
        given(categoryCursor.getName()).willReturn(categoryName);
        given(categoryCursor.getSection()).willReturn(categorySection);
        given(categoryCursor.getInterval()).willReturn(categoryInterval);
        given(categoryCursor.getType()).willReturn(categoryType);
        given(categoryCursor.getLevel()).willReturn(categoryLevel);

        long templateId = 12;
        String templateInterval = "interval";
        String templateType = "type";
        String templateComment = "comment";

        given(accountingTemplateCursor.moveToNext()).willReturn(true).willReturn(false);
        given(accountingTemplateCursor.getId()).willReturn(templateId);
        given(accountingTemplateCursor.getInterval()).willReturn(templateInterval);
        given(accountingTemplateCursor.getType()).willReturn(templateType);
        given(accountingTemplateCursor.getComment()).willReturn(templateComment);

        String templateMatchText = "text";

        given(templateMatchingCursor.moveToNext()).willReturn(true).willReturn(false);
        given(templateMatchingCursor.getText()).willReturn(templateMatchText);

        backupToJsonFileFunction.apply(backLocation);

        String backupContent = new String(outputStream.toByteArray());
        BackupJson backupJson = new ObjectMapper().readValue(backupContent, BackupJson.class);

        assertThat(backupJson.templateMatches).hasSize(1);
        TemplateMatchingJson json = backupJson.templateMatches.get(0);
        assertThat(json.text).isEqualTo(templateMatchText);
    }
}