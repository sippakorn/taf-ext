package com.aztec.azp.common.ext.dataprovider.csv.feed;

import java.util.ArrayList;
import java.util.List;

public class GivenBlockDTO {
    private List<JsonInputDTO> jsonData;

    public GivenBlockDTO() {
        jsonData = new ArrayList<>();
    }

    public List<JsonInputDTO> getJsonData() {
        return jsonData;
    }

    public void setJsonData(List<JsonInputDTO> jsonData) {
        this.jsonData = jsonData;
    }
}
