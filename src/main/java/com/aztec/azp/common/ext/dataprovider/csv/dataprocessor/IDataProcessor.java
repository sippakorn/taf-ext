package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;

public interface IDataProcessor {
    void process(String rawData, FeedRecordDTO feedData);
}
