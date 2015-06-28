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

    @JsonProperty("accounting")
    public List<AccountingJson> accounting = new ArrayList<>();

    @JsonProperty("templates")
    public List<TemplateJson> templates = new ArrayList<>();

    @JsonProperty("templateMatches")
    public List<TemplateMatchingJson> templateMatches = new ArrayList<>();

    @JsonProperty("intervals")
    public List<IntervalJson> intervals = new ArrayList<>();
}
