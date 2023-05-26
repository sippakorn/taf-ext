package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.ThenBlockDTO;

public class ThenStatusCodeDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        int statusCode = Integer.parseInt(rawData.trim());

        // TODO: validate statusCode 100 - 999
        if(inputDTO.getThen() == null){
            ThenBlockDTO then = new ThenBlockDTO();
            then.setStatusCode(statusCode);
            inputDTO.setThen(then);
        }else{
            ThenBlockDTO then = inputDTO.getThen();
            then.setStatusCode(statusCode);
        }
    }
}