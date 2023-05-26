package com.aztec.azp.common.ext.dataprovider.csv.filter.impl;


import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.FilterDataDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.ITestDataFilter;

import java.util.List;

public class RangeTestDataFilter implements ITestDataFilter {
    @Override
    public void filter(List<FeedRecordDTO> data, FilterDataDTO filterDataDTO) {
        int startIndex = filterDataDTO.getStartIndex() == -1 ? 0 : filterDataDTO.getStartIndex();
        int endIndex = filterDataDTO.getEndIndex() == -1 ? data.size() - 1 : filterDataDTO.getEndIndex();

        for (int i = data.size() - 1; i >= 0; i--) {
            if (i < startIndex || i > endIndex) {
                data.remove(i);
            }
        }
    }
}
