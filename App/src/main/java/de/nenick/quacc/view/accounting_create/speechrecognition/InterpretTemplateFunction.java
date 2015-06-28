package de.nenick.quacc.view.accounting_create.speechrecognition;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.speechrecognition.RecognizeTemplateFunction;
import de.nenick.quacc.speechrecognition.RecognizeTemplateFunction.SpeechTemplateResult;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.view.accounting_create.CreateAccountingView;

@EBean
public class InterpretTemplateFunction {

    @Bean
    RecognizeTemplateFunction recognizeTemplateFunction;

    @Bean
    ParseValueFromIntegerFunction parseValueFromIntegerFunction;

    public boolean apply(CreateAccountingView view, ArrayList<String> matches) {
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
            speechTemplateResult.accountingTemplateCursor.close();
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

    private void showTemplateValues(CreateAccountingView view, SpeechTemplateResult speechTemplateResult) {
        AccountingTemplateCursor accountingTemplateCursor = speechTemplateResult.accountingTemplateCursor;
        accountingTemplateCursor.moveToNext();

        view.setAccount(accountingTemplateCursor.getAccountName());
        view.setAccountingInterval(accountingTemplateCursor.getInterval());
        view.setAccountingType(accountingTemplateCursor.getType());
        view.setAccountingCategory(accountingTemplateCursor.getCategoryName());
        String comment = accountingTemplateCursor.getComment();
        if (!comment.isEmpty() && !speechTemplateResult.comment.isEmpty()) {
            comment += " " + speechTemplateResult.comment;
        } else {
            comment += speechTemplateResult.comment;
        }
        view.setComment(comment);
        view.setValue(parseValueFromIntegerFunction.apply(speechTemplateResult.value));
        accountingTemplateCursor.close();
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
