package com.aztec.azp.common.ext.dataprovider.csv.filter;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;

import java.util.List;

public interface ITestDataFilter {
    void filter(List<FeedRecordDTO> data, FilterDataDTO filterDataDTO);
}
