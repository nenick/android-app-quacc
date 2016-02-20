package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class BookingIntervalJson {

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

    @JsonProperty("direction")
    public String direction;

    @JsonProperty("amount")
    public int amount;

    public BookingIntervalJson() {}

    public BookingIntervalJson(long id, long accountId, long categoryId, String comment, String interval, String direction, Date dateStart, Date dateEnd, int amount) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.interval = interval;
        this.direction = direction;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.amount = amount;
    }
}
