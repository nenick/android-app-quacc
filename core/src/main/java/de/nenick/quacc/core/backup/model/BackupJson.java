package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BackupJson {

    @JsonProperty("accounts")
    public List<AccountJson> accounts = new ArrayList<>();

    @JsonProperty("categories")
    public List<CategoryJson> categories = new ArrayList<>();

    @JsonProperty("bookingEntries")
    public List<BookingEntryJson> bookingEntries = new ArrayList<>();

    @JsonProperty("bookingTemplates")
    public List<BookingTemplateJson> bookingTemplates = new ArrayList<>();

    @JsonProperty("bookingTemplateKeywords")
    public List<TemplateMatchingJson> bookingTemplateKeywords = new ArrayList<>();

    @JsonProperty("bookingIntervals")
    public List<BookingIntervalJson> bookingIntervals = new ArrayList<>();
}
