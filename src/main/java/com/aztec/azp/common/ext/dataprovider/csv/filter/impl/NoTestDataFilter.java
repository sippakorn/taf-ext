package com.aztec.azp.common.ext.dataprovider.csv.filter.impl;


import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.FilterDataDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.ITestDataFilter;

import java.util.List;

public class NoTestDataFilter implements ITestDataFilter {
    @Override
    public void filter(List<FeedRecordDTO> data, FilterDataDTO filterDataDTO) {
        return;
    }
}
