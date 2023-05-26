package com.aztec.azp.common.ext.dataprovider.csv.feed;

public class JsonOutputDTO {
    private Boolean isExist;
    private String selector;

    public JsonOutputDTO(){}

    public Boolean isExist() {
        return isExist;
    }

    public void setExist(Boolean exist) {
        isExist = exist;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }
}
