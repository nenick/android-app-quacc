package de.nenick.quacc.speechrecognition;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import de.nenick.quacc.R;
import de.nenick.quacc.database.AccountingType;
import de.nenick.quacc.database.CategoryDb;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class RecognizeCategoryFunction {

    @RootContext
    Context context;

    @Bean
    CategoryDb categoryDb;

    public SpeechResult apply(String recognizedText) {
        CategoryCursor categoryCursor = categoryDb.getAll();

        while (categoryCursor.moveToNext()) {
            String name = categoryCursor.getName();
            if (recognizedText.toLowerCase().contains(name.toLowerCase())) {
                categoryCursor.close();
                return new SpeechResult(name, recognizedText.toLowerCase().indexOf(name.toLowerCase()), name.length());
            }
        }

        categoryCursor.close();
        return null;
    }

}
