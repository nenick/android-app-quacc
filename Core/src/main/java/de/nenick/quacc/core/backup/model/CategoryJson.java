package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class CategoryJson {

    @JsonProperty("id")
    public long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("section")
    public String section;

    @JsonProperty("interval")
    public String interval;

    @JsonProperty("type")
    public String type;

    @JsonProperty("level")
    public int level;

    public CategoryJson() {}

    public CategoryJson(long id, String name, String section, String interval, String type, int level) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.interval = interval;
        this.type = type;
        this.level = level;
    }
}
