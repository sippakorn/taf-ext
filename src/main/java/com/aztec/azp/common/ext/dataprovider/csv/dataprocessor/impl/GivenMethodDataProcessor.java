package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.ModificationMethod;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonInputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.GivenBlockDTO;

import java.util.Arrays;

public class GivenMethodDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        ModificationMethod modificationMethod = ModificationMethod.NO_ACTION;
        if (rawData == null) {
            // TODO: add log here
        } else if (rawData.equals("DELETE")) {
            modificationMethod = ModificationMethod.DELETE;
        } else if (rawData.equals("UPDATE")) {
            modificationMethod = ModificationMethod.UPDATE;
        }

        if (inputDTO.getGiven() == null) {
            GivenBlockDTO given = new GivenBlockDTO();
            JsonInputDTO json = new JsonInputDTO();
            json.setMethod(modificationMethod);
            given.setJsonData(Arrays.asList(json));
            inputDTO.setGiven(given);
        } else {
            GivenBlockDTO given = inputDTO.getGiven();
            if (given.getJsonData() != null && !given.getJsonData().isEmpty()) {
                JsonInputDTO jsonInputDTO = given.getJsonData().get(0);
                jsonInputDTO.setMethod(modificationMethod);
            } else {
                JsonInputDTO jsonInputDTO = new JsonInputDTO();
                jsonInputDTO.setMethod(modificationMethod);
                given.setJsonData(Arrays.asList(jsonInputDTO));
                inputDTO.setGiven(given);
            }
        }
    }
}