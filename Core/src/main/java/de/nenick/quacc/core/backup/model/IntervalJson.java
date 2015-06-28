package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class IntervalJson {

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

    @JsonProperty("dateStart")
    public Date dateStart;

    @JsonProperty("dateEnd")
    public Date dateEnd;

    @JsonProperty("type")
    public String type;

    @JsonProperty("value")
    public int value;

    public IntervalJson() {}

    public IntervalJson(long id, long accountId, long categoryId, String comment, String interval, String type, Date dateStart, Date dateEnd, int value) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.interval = interval;
        this.type = type;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.value = value;
    }
}
