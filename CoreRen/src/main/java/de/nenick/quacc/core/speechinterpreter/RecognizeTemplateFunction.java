package de.nenick.quacc.core.speechinterpreter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.core.utils.StringPartUtil;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateRepository;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateSpecById;
import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordRepository;
import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordSpecAll;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordCursor;

@EBean
public class RecognizeTemplateFunction {

    public static class SpeechTemplateResult {
        public final int value;
        public final String comment;
        public final BookingTemplateCursor bookingTemplateCursor;

        public SpeechTemplateResult(int value, String comment, BookingTemplateCursor bookingTemplateCursor) {
            this.value = value;
            this.comment = comment;
            this.bookingTemplateCursor = bookingTemplateCursor;
        }
    }

    @Bean
    BookingTemplateRepository bookingTemplateRepository;

    @Bean
    BookingTemplateKeywordRepository bookingTemplateKeywordRepository;

    @Bean
    RecognizeValueFunction recognizeValueFunction;

    public SpeechTemplateResult apply(String recognizedText) {
        BookingTemplateKeywordCursor bookingTemplateKeywordCursor = bookingTemplateKeywordRepository.query(new BookingTemplateKeywordSpecAll());
        while (bookingTemplateKeywordCursor.moveToNext()) {
            String templateText = bookingTemplateKeywordCursor.getText();
            if(recognizedText.toLowerCase().contains(templateText.toLowerCase())) {
                RecognizeValueFunction.SpeechValueResult valueRecognitionResult = recognizeValueFunction.apply(recognizedText);
                String notRecognizedText = recognizedText;
                int value = 0;
                if(valueRecognitionResult != null) {
                    notRecognizedText = StringPartUtil.removePartWithLength(notRecognizedText, valueRecognitionResult.start, valueRecognitionResult.length);
                    value = valueRecognitionResult.value;
                }
                notRecognizedText = StringPartUtil.removePartWithLength(notRecognizedText, notRecognizedText.toLowerCase().indexOf(templateText.toLowerCase()), templateText.length());

                BookingTemplateCursor accountingTemplateCursor = bookingTemplateRepository.query(new BookingTemplateSpecById(bookingTemplateKeywordCursor.getBookingTemplateId()));
                SpeechTemplateResult speechTemplateResult = new SpeechTemplateResult(value, notRecognizedText, accountingTemplateCursor);
                bookingTemplateKeywordCursor.close();
                return speechTemplateResult;
            }
        }

        bookingTemplateKeywordCursor.close();
        return null;
    }
}
