package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class BookingEntryJson {

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

    @JsonProperty("direction")
    public String direction;

    @JsonProperty("amount")
    public int amount;

    public BookingEntryJson() {}

    public BookingEntryJson(long id, long accountId, long categoryId, String comment, String interval, String direction, Date date, int amount) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.interval = interval;
        this.direction = direction;
        this.date = date;
        this.amount = amount;
    }
}
