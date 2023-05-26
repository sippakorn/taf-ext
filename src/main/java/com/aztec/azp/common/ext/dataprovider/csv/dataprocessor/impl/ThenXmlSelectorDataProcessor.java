package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.ThenBlockDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.XmlOutputDTO;

import java.util.Arrays;

public class ThenXmlSelectorDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        // TODO: if possible validate rawData here
        if(inputDTO.getThen() == null){
            ThenBlockDTO then = new ThenBlockDTO();
            XmlOutputDTO xml = new XmlOutputDTO();
            xml.setSelector(rawData);
            then.setXmlData(Arrays.asList(xml));
            inputDTO.setThen(then);
        }else{
            ThenBlockDTO then = inputDTO.getThen();
            if(then.getXmlData() != null || !then.getXmlData().isEmpty()){
                XmlOutputDTO xml = then.getXmlData().get(0);
                xml.setSelector(rawData);
            }
        }
    }
}