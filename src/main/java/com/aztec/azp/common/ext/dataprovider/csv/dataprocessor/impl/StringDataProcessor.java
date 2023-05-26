package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StringDataProcessor implements IDataProcessor {
    private String fieldName;

    public StringDataProcessor(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void process(String rawData, FeedRecordDTO inputDTO) {
        Method method;
        try {
            method = FeedRecordDTO.class.getMethod("set" + fieldName, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        try {
            method.invoke(inputDTO, rawData);
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }
}
