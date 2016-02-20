package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class BookingTemplateJson {

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

    @JsonProperty("direction")
    public String direction;

    public BookingTemplateJson() {}

    public BookingTemplateJson(long id, long accountId, long categoryId, String comment, String interval, String direction) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.interval = interval;
        this.direction = direction;
    }
}
