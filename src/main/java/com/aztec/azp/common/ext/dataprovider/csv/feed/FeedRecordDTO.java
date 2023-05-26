package com.aztec.azp.common.ext.dataprovider.csv.feed;

public class FeedRecordDTO {
    private String testId;
    private String message;
    private String document;
    private GivenBlockDTO given;
    private ThenBlockDTO then;
    private boolean disable;

    public FeedRecordDTO() {
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public GivenBlockDTO getGiven() {
        return given;
    }

    public void setGiven(GivenBlockDTO given) {
        this.given = given;
    }

    public ThenBlockDTO getThen() {
        return then;
    }

    public void setThen(ThenBlockDTO then) {
        this.then = then;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
