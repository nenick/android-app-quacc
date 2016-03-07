package de.nenick.quacc.view.accounting_edit.speechrecognition;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;


import de.nenick.quacc.core.speechinterpreter.RecognizeTemplateFunction;
import de.nenick.quacc.core.speechinterpreter.RecognizeTemplateFunction.SpeechTemplateResult;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.toolscollection.amountparser.AmountParser;
import de.nenick.toolscollection.amountparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.view.accounting_edit.EditAccountingView;

@EBean
public class InterpretTemplateFunction {

    @Bean
    RecognizeTemplateFunction recognizeTemplateFunction;

    public boolean apply(EditAccountingView view, ArrayList<String> matches) {
        List<SpeechTemplateResult> speechTemplateResults = searchForTemplateMatches(matches);
        if (speechTemplateResults.isEmpty()) {
            return false;
        }
        SpeechTemplateResult resultWithBestMatch = findResultWithBestMatch(speechTemplateResults);
        showTemplateValues(view, resultWithBestMatch);
        closeAllCursors(speechTemplateResults);
        return true;
    }

    private void closeAllCursors(List<SpeechTemplateResult> speechTemplateResults) {
        for (SpeechTemplateResult speechTemplateResult : speechTemplateResults) {
            speechTemplateResult.bookingTemplateCursor.close();
        }
    }

    private List<SpeechTemplateResult> searchForTemplateMatches(ArrayList<String> matches) {
        List<SpeechTemplateResult> speechTemplateResults = new ArrayList<>();
        for (String match : matches) {
            SpeechTemplateResult speechTemplateResult = recognizeTemplateFunction.apply(match);
            if (speechTemplateResult != null) {
                speechTemplateResults.add(speechTemplateResult);
            }
        }
        return speechTemplateResults;
    }

    private void showTemplateValues(EditAccountingView view, SpeechTemplateResult speechTemplateResult) {
        BookingTemplateCursor bookingTemplateCursor = speechTemplateResult.bookingTemplateCursor;
        bookingTemplateCursor.moveToNext();

        view.setAccount(bookingTemplateCursor.getAccountName());
        view.setAccountingInterval(bookingTemplateCursor.getInterval());
        view.setAccountingType(bookingTemplateCursor.getDirection());
        view.setAccountingCategory(bookingTemplateCursor.getCategoryName());
        String comment = bookingTemplateCursor.getComment();
        if (!comment.isEmpty() && !speechTemplateResult.comment.isEmpty()) {
            comment += " " + speechTemplateResult.comment;
        } else {
            comment += speechTemplateResult.comment;
        }
        view.setComment(comment);
        view.setValue(AmountParser.asString(speechTemplateResult.value));
        bookingTemplateCursor.close();
    }

    private SpeechTemplateResult findResultWithBestMatch(List<SpeechTemplateResult> speechTemplateResults) {
        SpeechTemplateResult speechTemplateResultWithShortestComment = null;
        for (SpeechTemplateResult speechTemplateResult : speechTemplateResults) {
            if (speechTemplateResultWithShortestComment == null) {
                speechTemplateResultWithShortestComment = speechTemplateResult;
            } else {
                if (speechTemplateResult.comment.length() < speechTemplateResultWithShortestComment.comment.length()) {
                    speechTemplateResultWithShortestComment = speechTemplateResult;
                }
            }
        }
        return speechTemplateResultWithShortestComment;
    }
}
