package com.aztec.azp.common.ext.dataprovider.csv;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.IDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;

public class ColumnData {
    private ColumnName columnName;
    private int index;
    private IDataProcessor processor;

    public ColumnData(ColumnName columnName, int index, IDataProcessor processor){
        this.columnName = columnName;
        this.index = index;
        this.processor = processor;
    }

    public void process(String rawData, FeedRecordDTO inputDTO){
        processor.process(rawData, inputDTO);
    }

    public int getIndex(){
        return this.index;
    }

    public ColumnName getColumnName(){
        return this.columnName;
    }
}
