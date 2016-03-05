package de.nenick.quacc.core.backup.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class AccountJson {

    @JsonProperty("id")
    public long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("initial_value")
    public int initialValue;

    public AccountJson() {}

    public AccountJson(long id, String name, int initialValue) {
        this.id = id;
        this.name = name;
        this.initialValue = initialValue;
    }
}
