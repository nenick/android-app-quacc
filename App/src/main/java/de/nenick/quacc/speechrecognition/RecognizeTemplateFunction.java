package de.nenick.quacc.speechrecognition;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.StringPartUtil;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingCursor;
import de.nenick.quacc.database.template.AccountingTemplateDb;
import de.nenick.quacc.database.template.TemplateMatchingDb;

@EBean
public class RecognizeTemplateFunction {

    public static class SpeechTemplateResult {
        public final int value;
        public final String comment;
        public final AccountingTemplateCursor accountingTemplateCursor;

        public SpeechTemplateResult(int value, String comment, AccountingTemplateCursor accountingTemplateCursor) {
            this.value = value;
            this.comment = comment;
            this.accountingTemplateCursor = accountingTemplateCursor;
        }
    }

    @Bean
    AccountingTemplateDb accountingTemplateDb;

    @Bean
    TemplateMatchingDb templateMatchingDb;

    @Bean
    RecognizeValueFunction recognizeValueFunction;

    public SpeechTemplateResult apply(String recognizedText) {
        TemplateMatchingCursor templateMatchingEntry = templateMatchingDb.getAll();
        while (templateMatchingEntry.moveToNext()) {
            String templateText = templateMatchingEntry.getText();
            if(recognizedText.toLowerCase().contains(templateText.toLowerCase())) {
                RecognizeValueFunction.SpeechValueResult valueRecognitionResult = recognizeValueFunction.apply(recognizedText);
                String notRecognizedText = recognizedText;
                int value = 0;
                if(valueRecognitionResult != null) {
                    notRecognizedText = StringPartUtil.removePartWithLength(notRecognizedText, valueRecognitionResult.start, valueRecognitionResult.length);
                    value = valueRecognitionResult.value;
                }
                notRecognizedText = StringPartUtil.removePartWithLength(notRecognizedText, notRecognizedText.toLowerCase().indexOf(templateText.toLowerCase()), templateText.length());

                AccountingTemplateCursor accountingTemplateCursor = accountingTemplateDb.getById(templateMatchingEntry.getAccountingTemplateId());
                SpeechTemplateResult speechTemplateResult = new SpeechTemplateResult(value, notRecognizedText, accountingTemplateCursor);
                templateMatchingEntry.close();
                return speechTemplateResult;
            }
        }

        templateMatchingEntry.close();
        return null;
    }
}
