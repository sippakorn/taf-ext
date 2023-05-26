package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;


import com.aztec.azp.common.ext.dataprovider.csv.CsvColumnConstants;

public abstract class AbstractIsExistDataProcessor {
    public Boolean parseIsExist(String rawData) {
        if (rawData == null) {
            return null;
        }
        
        Boolean isExist = null;
        if(CsvColumnConstants.FALSE_LIST.contains(rawData.toUpperCase())) {
            isExist = false;
        } else if(CsvColumnConstants.TRUE_LIST.contains(rawData.toUpperCase())) {
            isExist = true;
        }
        return isExist;
    }
}
