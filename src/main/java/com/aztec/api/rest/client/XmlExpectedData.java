package com.aztec.api.rest.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XmlExpectedData {
    @JsonProperty("isExist")
    private boolean isExist;

    @JsonProperty("value")
    private String value;

    @JsonProperty("isExist")
    public boolean isExist() {
        return isExist;
    }

    @JsonProperty("isExist")
    public void setExist(boolean exist) {
        isExist = exist;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

}
