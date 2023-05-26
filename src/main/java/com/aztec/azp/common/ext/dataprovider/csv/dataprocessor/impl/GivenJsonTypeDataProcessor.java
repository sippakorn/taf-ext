package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonInputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonNodeType;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.GivenBlockDTO;

import java.util.Arrays;

public class GivenJsonTypeDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        JsonNodeType type = JsonNodeType.STRING;
        if (rawData != null && !rawData.isEmpty()) {
            try {
                type = JsonNodeType.valueOf(rawData);
            } catch (IllegalArgumentException ex) {
                type = JsonNodeType.STRING;
            }
        }

        if (inputDTO.getGiven() == null) {
            GivenBlockDTO given = new GivenBlockDTO();
            JsonInputDTO json = new JsonInputDTO();
            json.setType(type);
            given.setJsonData(Arrays.asList(json));
            inputDTO.setGiven(given);
        } else {
            GivenBlockDTO given = inputDTO.getGiven();
            if (given.getJsonData() != null && !given.getJsonData().isEmpty()) {
                JsonInputDTO jsonInputDTO = given.getJsonData().get(0);
                jsonInputDTO.setType(type);
            } else {
                JsonInputDTO jsonInputDTO = new JsonInputDTO();
                jsonInputDTO.setType(type);
                given.setJsonData(Arrays.asList(jsonInputDTO));
                inputDTO.setGiven(given);
            }
        }
    }
}