package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonOutputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.ThenBlockDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.XmlOutputDTO;

import java.util.Arrays;

public class ThenXmlIsExistDataProcessor extends AbstractIsExistDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        Boolean isExist = parseIsExist(rawData);
        if(inputDTO.getThen() == null){
            ThenBlockDTO then = new ThenBlockDTO();
            XmlOutputDTO xml = new XmlOutputDTO();
            xml.setExist(isExist);
            then.setXmlData(Arrays.asList(xml));
            inputDTO.setThen(then);
        }else{
            ThenBlockDTO then = inputDTO.getThen();
            if(then.getXmlData() != null || !then.getXmlData().isEmpty()){
                JsonOutputDTO json = then.getJsonData().get(0);
                json.setExist(isExist);
            }
        }
    }
}