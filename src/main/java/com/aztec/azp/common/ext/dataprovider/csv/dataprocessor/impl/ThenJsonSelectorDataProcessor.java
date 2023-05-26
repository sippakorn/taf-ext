package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonOutputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.ThenBlockDTO;

import java.util.Arrays;

public class ThenJsonSelectorDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        // TODO: if possible validate rawData here
        if(inputDTO.getThen() == null){
            ThenBlockDTO then = new ThenBlockDTO();
            JsonOutputDTO json = new JsonOutputDTO();
            json.setSelector(rawData);
            then.setJsonData(Arrays.asList(json));
            inputDTO.setThen(then);
        }else{
            ThenBlockDTO then = inputDTO.getThen();
            if(then.getJsonData() != null || !then.getJsonData().isEmpty()){
                JsonOutputDTO json = then.getJsonData().get(0);
                json.setSelector(rawData);
            }
        }
    }
}