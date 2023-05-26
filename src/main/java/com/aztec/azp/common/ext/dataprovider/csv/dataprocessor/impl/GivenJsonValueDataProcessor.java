package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonInputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.GivenBlockDTO;

import java.util.Arrays;

public class GivenJsonValueDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        if (inputDTO.getGiven() == null) {
            GivenBlockDTO given = new GivenBlockDTO();
            JsonInputDTO json = new JsonInputDTO();
            json.setValue(rawData);
            given.setJsonData(Arrays.asList(json));
            inputDTO.setGiven(given);
        } else {
            GivenBlockDTO given = inputDTO.getGiven();
            if (given.getJsonData() != null && !given.getJsonData().isEmpty()) {
                JsonInputDTO jsonInputDTO = given.getJsonData().get(0);
                jsonInputDTO.setValue(rawData);
            } else {
                JsonInputDTO jsonInputDTO = new JsonInputDTO();
                jsonInputDTO.setValue(rawData);
                given.setJsonData(Arrays.asList(jsonInputDTO));
                inputDTO.setGiven(given);
            }
        }
    }
}