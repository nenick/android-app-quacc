package de.nenick.quacc.speechrecognition;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingCursor;
import de.nenick.quacc.database.template.AccountingTemplateDb;
import de.nenick.quacc.database.template.TemplateMatchingDb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RecognizeTemplateFunctionTest {

    @InjectMocks
    RecognizeTemplateFunction recognizeTemplateFunction;

    @Mock
    TemplateMatchingDb templateMatchingDb;

    @Mock
    AccountingTemplateDb accountingTemplateDb;

    @Mock
    AccountingTemplateCursor accountingTemplateCursor;

    @Mock
    TemplateMatchingCursor templateMatchingCursor;

    @Mock
    RecognizeValueFunction recognizeValueFunction;

    RecognizeTemplateFunction.SpeechTemplateResult result;

    String templateText = "Mittagessen";
    long templateId = 42;

    String templateTextWithComment = templateText + " Pizza ";
    private final String priceText = "5 Euro";
    String recognizedText = templateTextWithComment + priceText;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        given(templateMatchingDb.getAll()).willReturn(templateMatchingCursor);
        given(accountingTemplateDb.getById(templateId)).willReturn(accountingTemplateCursor);
    }

    @Test
    public void shouldCloseCursor() {
        whenApplyWith(recognizedText);
        verify(templateMatchingCursor).close();
    }

    @Test
    public void shouldReportNullIfNothingMatch() {
        whenApplyWith(recognizedText);
        assertThat(result).isNull();
    }

    @Test
    public void shouldReportMatch() {
        given(templateMatchingCursor.moveToNext()).willReturn(true).willReturn(false);
        given(templateMatchingCursor.getAccountingTemplateId()).willReturn(templateId);
        given(templateMatchingCursor.getText()).willReturn(templateText);
        given(recognizeValueFunction.apply(recognizedText)).willReturn(new RecognizeValueFunction.SpeechValueResult(500, templateTextWithComment.length(), priceText.length()));
        whenApplyWith(recognizedText);
        assertThat(result).isNotNull();
        assertThat(result.accountingTemplateCursor).isEqualTo(accountingTemplateCursor);
        assertThat(result.value).isEqualTo(500);
        assertThat(result.comment).isEqualTo("Pizza");
    }

    private void whenApplyWith(String recognizedText) {
        result = recognizeTemplateFunction.apply(recognizedText);
    }
}