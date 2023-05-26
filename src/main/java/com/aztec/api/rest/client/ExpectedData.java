package com.aztec.api.rest.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExpectedData {

    @JsonProperty("testID")
    private String testId;

    @JsonProperty("testMessage")
    private String testMessage;

    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("xmlData")
    private List<XmlExpectedData> xmlData;

    @JsonProperty("jsonData")
    private List<JsonExpectedData> jsonData;

    @JsonProperty("testID")
    public String getTestId() {
        return testId;
    }

    @JsonProperty("testID")
    public void setTestId(String testId) {
        this.testId = testId;
    }

    @JsonProperty("testMessage")
    public String getTestMessage() {
        return testMessage;
    }

    @JsonProperty("testMessage")
    public void setTestMessage(String testMessage) {
        this.testMessage = testMessage;
    }

    @JsonProperty("statusCode")
    public int getStatusCode() {
        return statusCode;
    }

    @JsonProperty("statusCode")
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("xmlData")
    public List<XmlExpectedData> getXmlData() {
        return xmlData;
    }

    @JsonProperty("xmlData")
    public void setXmlData(List<XmlExpectedData> xmlData) {
        this.xmlData = xmlData;
    }

    @JsonProperty("jsonData")
    public List<JsonExpectedData> getJsonData() {
        return jsonData;
    }

    @JsonProperty("jsonData")
    public void setJsonData(List<JsonExpectedData> jsonData) {
        this.jsonData = jsonData;
    }
}
