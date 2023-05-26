package com.aztec.azp.common.ext.dataprovider.csv.filter;

import com.aztec.azp.common.ext.dataprovider.csv.filter.impl.NoTestDataFilter;
import com.aztec.azp.common.ext.dataprovider.csv.filter.impl.RandomSelectTestDataFilter;
import com.aztec.azp.common.ext.dataprovider.csv.filter.impl.RangeTestDataFilter;
import com.aztec.azp.common.ext.dataprovider.csv.filter.impl.TestIdsTestDataFilter;

public class TestDataFilterFactory {

    public ITestDataFilter getFilter(FilterDataDTO filterData) {
        if (filterData.getTestIds() != null && filterData.getTestIds().length > 0) {
            return new TestIdsTestDataFilter();
        } else if (filterData.getStartIndex() != -1 || filterData.getEndIndex() != -1) {
            return new RangeTestDataFilter();
        } else if (filterData.getRandomPercentExecute() > 0.0d) {
            return new RandomSelectTestDataFilter();
        } else {
            return new NoTestDataFilter();
        }
    }
}
