package com.aztec.azp.common.ext.dataprovider.csv.filter.impl;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.FilterDataDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.ITestDataFilter;

import java.util.Arrays;
import java.util.List;

public class TestIdsTestDataFilter implements ITestDataFilter {

    @Override
    public void filter(List<FeedRecordDTO> data, FilterDataDTO filterDataDTO) {
        List<String> testIds = Arrays.asList(filterDataDTO.getTestIds());
        outer:
        for (int i = data.size() - 1; i >= 0; i--) {
            FeedRecordDTO feedDataDTO = data.get(i);
            for (String keyword : testIds) {
                if (feedDataDTO.getTestId().startsWith(keyword)) {
                    continue outer;
                }
            }
            data.remove(i);
        }
    }
}
