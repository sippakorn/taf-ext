package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;


import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonInputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.GivenBlockDTO;

import java.util.Arrays;

public class GivenJsonPathDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        // TODO: if possible validate rawData here
        if (inputDTO.getGiven() == null) {
            GivenBlockDTO given = new GivenBlockDTO();
            JsonInputDTO json = new JsonInputDTO();
            json.setPath(rawData);
            given.setJsonData(Arrays.asList(json));
            inputDTO.setGiven(given);
        } else {
            GivenBlockDTO given = inputDTO.getGiven();
            if (given.getJsonData() != null && !given.getJsonData().isEmpty()) {
                JsonInputDTO jsonInputDTO = given.getJsonData().get(0);
                jsonInputDTO.setPath(rawData);
            } else {
                JsonInputDTO jsonInputDTO = new JsonInputDTO();
                jsonInputDTO.setPath(rawData);
                given.setJsonData(Arrays.asList(jsonInputDTO));
                inputDTO.setGiven(given);
            }
        }
    }
}
