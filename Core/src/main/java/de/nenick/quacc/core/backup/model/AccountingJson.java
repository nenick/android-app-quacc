package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class AccountingJson {

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

    @JsonProperty("date")
    public Date date;

    @JsonProperty("type")
    public String type;

    @JsonProperty("value")
    public int value;

    public AccountingJson() {}

    public AccountingJson(long id, long accountId, long categoryId, String comment, String interval, String type, Date date, int value) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.interval = interval;
        this.type = type;
        this.date = date;
        this.value = value;
    }
}
