package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class TemplateMatchingJson {

    @JsonProperty("text")
    public String text;

    @JsonProperty("bookingTemplateId")
    public long bookingTemplateId;

    public TemplateMatchingJson() {}

    public TemplateMatchingJson(String text, long bookingTemplateId) {
        this.text = text;
        this.bookingTemplateId = bookingTemplateId;
    }
}
