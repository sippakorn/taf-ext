package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonOutputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.ThenBlockDTO;

import java.util.Arrays;

public class ThenJsonIsExistDataProcessor extends AbstractIsExistDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        Boolean isExist = parseIsExist(rawData);
        if(inputDTO.getThen() == null){
            ThenBlockDTO then = new ThenBlockDTO();
            JsonOutputDTO json = new JsonOutputDTO();
            json.setExist(isExist);
            then.setJsonData(Arrays.asList(json));
            inputDTO.setThen(then);
        }else{
            ThenBlockDTO then = inputDTO.getThen();
            if(then.getJsonData() != null || !then.getJsonData().isEmpty()){
                JsonOutputDTO json = then.getJsonData().get(0);
                json.setExist(isExist);
            }
        }
    }
}