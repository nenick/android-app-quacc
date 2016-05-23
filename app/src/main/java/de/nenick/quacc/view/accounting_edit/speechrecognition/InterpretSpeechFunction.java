package de.nenick.quacc.view.accounting_edit.speechrecognition;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.core.speechinterpreter.RecognizeAccountingIntervalFunction;
import de.nenick.quacc.core.speechinterpreter.RecognizeAccountingTypeFunction;
import de.nenick.quacc.core.speechinterpreter.RecognizeCategoryFunction;
import de.nenick.quacc.core.speechinterpreter.RecognizeValueFunction;
import de.nenick.quacc.core.speechinterpreter.SpeechResult;
import de.nenick.quacc.core.utils.StringPartUtil;
import de.nenick.quacc.tools.AmountParser;
import de.nenick.quacc.view.accounting_edit.EditAccountingView;

@EBean
public class InterpretSpeechFunction {

    @Bean
    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction;

    @Bean
    RecognizeAccountingIntervalFunction recognizeAccountingIntervalFunction;

    @Bean
    RecognizeCategoryFunction recognizeCategoryFunction;

    @Bean
    RecognizeValueFunction recognizeValueFunction;

    @Bean
    AmountParser amountParser;

    public void apply(EditAccountingView view, ArrayList<String> matches) {
        List<RecognitionResult> recognitionResults = new ArrayList<>();
        for (String match : matches) {
            RecognitionResult recognitionResult = new RecognitionResult();
            recognitionResults.add(recognitionResult);

            String notMatchedText = match;
            notMatchedText = interpretAccountingType(notMatchedText, recognitionResult);
            notMatchedText = interpretAccountingInterval(notMatchedText, recognitionResult);
            notMatchedText = interpretAccountingCategory(notMatchedText, recognitionResult);
            notMatchedText = interpretAccountingValue(notMatchedText, recognitionResult);
            recognitionResult.comment = notMatchedText.trim();
        }

        RecognitionResult resultWithBestMatch = findResultWithBestMatch(recognitionResults);
        showRecognizedValues(view, resultWithBestMatch);
    }

    private String interpretAccountingCategory(String recognisedText, RecognitionResult recognitionResult) {
        SpeechResult result = recognizeCategoryFunction.apply(recognisedText);
        if(result != null) {
            recognitionResult.category = result.value;
            return StringPartUtil.removePartWithLength(recognisedText, result.start, result.length);
        }
        return recognisedText;
    }

    private String interpretAccountingType(String recognisedText, RecognitionResult recognitionResult) {
        SpeechResult result = recognizeAccountingTypeFunction.apply(recognisedText);
        if (result != null) {
            recognitionResult.type = result.value;
            return StringPartUtil.removePartWithLength(recognisedText, result.start, result.length);
        }
        return recognisedText;
    }


    private String interpretAccountingInterval(String recognisedText, RecognitionResult recognitionResult) {
        SpeechResult result = recognizeAccountingIntervalFunction.apply(recognisedText);
        if (result != null) {
            recognitionResult.interval = result.value;
            return StringPartUtil.removePartWithLength(recognisedText, result.start, result.length);
        }
        return recognisedText;
    }

    private String interpretAccountingValue(String recognisedText, RecognitionResult recognitionResult) {
        RecognizeValueFunction.SpeechValueResult result = recognizeValueFunction.apply(recognisedText);
        if (result != null) {
            recognitionResult.value = amountParser.asString(result.value);
            return StringPartUtil.removePartWithLength(recognisedText, result.start, result.length);
        }
        return recognisedText;
    }

    private void showRecognizedValues(EditAccountingView view, RecognitionResult recognitionResult) {
        if(recognitionResult.interval != null) {
            view.setAccountingInterval(recognitionResult.interval);
        }
        if(recognitionResult.type != null) {
            view.setAccountingType(recognitionResult.type);
        }
        if(recognitionResult.category != null) {
            view.setAccountingCategory(recognitionResult.category);
        }
        if(recognitionResult.value != null) {
            view.setValue(recognitionResult.value);
        }
        if(!recognitionResult.comment.isEmpty()) {
            view.setComment(recognitionResult.comment);
        }
    }

    private RecognitionResult findResultWithBestMatch(List<RecognitionResult> recognitionResults) {
        RecognitionResult recognitionResultWithShortestComment = null;
        for (RecognitionResult recognitionResult : recognitionResults) {
            if (recognitionResultWithShortestComment == null) {
                recognitionResultWithShortestComment = recognitionResult;
            } else {
                if (recognitionResult.comment.length() < recognitionResultWithShortestComment.comment.length()) {
                    recognitionResultWithShortestComment = recognitionResult;
                }
            }
        }
        return recognitionResultWithShortestComment;
    }
}
