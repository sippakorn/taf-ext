package com.aztec.api.rest.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CsvRestTestConfig extends RestTestConfig {
    @JsonProperty("expectedData")
    private ExpectedData expectedData;

    @JsonProperty("expectedData")
    public ExpectedData getExpectedData() {
        return expectedData;
    }

    @JsonProperty("expectedData")
    public void setExpectedData(ExpectedData expectedData) {
        this.expectedData = expectedData;
    }
}
