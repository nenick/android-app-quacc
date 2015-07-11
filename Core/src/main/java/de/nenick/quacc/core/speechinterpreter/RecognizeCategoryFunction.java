package de.nenick.quacc.core.speechinterpreter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.category.CategoryDb;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class RecognizeCategoryFunction {

    @Bean
    CategoryDb categoryDb;

    public SpeechResult apply(String recognizedText) {
        SpeechResult speechResult = null;
        CategoryCursor categoryCursor = categoryDb.getAll();

        while (categoryCursor.moveToNext()) {
            String name = categoryCursor.getName();
            if (recognizedText.toLowerCase().contains(name.toLowerCase())) {
                if(speechResult == null || speechResult.length < name.length()) {
                    speechResult = new SpeechResult(name, recognizedText.toLowerCase().indexOf(name.toLowerCase()), name.length());
                }
            }
        }

        categoryCursor.close();
        return speechResult;
    }

}
