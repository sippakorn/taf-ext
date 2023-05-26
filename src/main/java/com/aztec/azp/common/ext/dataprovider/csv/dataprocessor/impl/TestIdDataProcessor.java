package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.CsvColumnConstants;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;

public class TestIdDataProcessor implements IDataProcessor {
    @Override
    public void process(String rawData, FeedRecordDTO feedData) {
        if (!CsvColumnConstants.AND.toLowerCase().equalsIgnoreCase(rawData.trim().toLowerCase())) {
            feedData.setTestId(rawData);
        }
    }
}
