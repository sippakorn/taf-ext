package com.aztec.azp.common.ext.dataprovider.csv.feed;

import java.util.ArrayList;
import java.util.List;

public class ThenBlockDTO {
    private int statusCode;
    private List<JsonOutputDTO> jsonData;
    private List<XmlOutputDTO> xmlData;

    public ThenBlockDTO(){
        this.jsonData = new ArrayList<>();
        this.xmlData = new ArrayList<>();
    }

    public List<JsonOutputDTO> getJsonData() {
        return jsonData;
    }

    public void setJsonData(List<JsonOutputDTO> jsonData) {
        this.jsonData = jsonData;
    }

    public List<XmlOutputDTO> getXmlData() {
        return xmlData;
    }

    public void setXmlData(List<XmlOutputDTO> xmlData) {
        this.xmlData = xmlData;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
