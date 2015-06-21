package de.nenick.quacc.template;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.template.TemplateMatchingDb;

@EBean
public class CreateTemplateMatchFunction {

    @Bean
    TemplateMatchingDb templateMatchingDb;

    public void apply(String text, long templateId) {
        templateMatchingDb.insert(text, templateId);
    }
}
