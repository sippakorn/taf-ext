package com.aztec.azp.common.ext.dataprovider.csv.filter.impl;


import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.FilterDataDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.ITestDataFilter;

import java.util.List;

public class RandomSelectTestDataFilter implements ITestDataFilter {

    @Override
    public void filter(List<FeedRecordDTO> data, FilterDataDTO filterDataDTO) {
        int numberOfTestData = (int) Math.ceil(data.size() * filterDataDTO.getRandomPercentExecute() / 100);

        while (data.size() > numberOfTestData) {
            double candidate = Math.floor(Math.random() * data.size());
            int cIndex = (int) candidate;
            if (cIndex < data.size() && cIndex > 0) {
                data.remove(cIndex);
            }
        }
    }
}
