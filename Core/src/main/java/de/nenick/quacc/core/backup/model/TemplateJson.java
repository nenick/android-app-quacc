package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class TemplateJson {

    @JsonProperty("id")
    public long id;

    @JsonProperty("accountId")
    public long accountId;

    @JsonProperty("categoryId")
    public long categoryId;

    @JsonProperty("comment")
    public String comment;

    @JsonProperty("interval")
    public String interval;

    @JsonProperty("type")
    public String type;

    public TemplateJson() {}

    public TemplateJson(long id, long accountId, long categoryId, String comment, String interval, String type) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.interval = interval;
        this.type = type;
    }
}
