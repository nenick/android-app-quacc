package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class TemplateMatchingJson {

    @JsonProperty("text")
    public String text;

    @JsonProperty("templateId")
    public long templateId;

    public TemplateMatchingJson() {}

    public TemplateMatchingJson(String text, long templateId) {
        this.text = text;
        this.templateId = templateId;
    }
}
