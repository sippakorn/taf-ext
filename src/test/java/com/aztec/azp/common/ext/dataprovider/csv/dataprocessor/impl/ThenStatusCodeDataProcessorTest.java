package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.ThenBlockDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ThenStatusCodeDataProcessorTest extends BaseDataProcessorTest {
    private ThenStatusCodeDataProcessor dataProcessor;

    @Before
    public void setup(){
        dataProcessor = new ThenStatusCodeDataProcessor();
    }

    @Test
    public void test_process_rawData_is_String(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("string", feedRecordDTO);

        // Then
        // expect NPE
    }

    @Test
    public void test_process_rawData_is_0() {
        //Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("0", feedRecordDTO);

        // Then
        assertEquals(0, feedRecordDTO.getThen().getStatusCode());
    }

    @Test
    public void test_process_rawData_is__200_() {
        //Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(" 200 ", feedRecordDTO);

        // Then
        assertEquals(200, feedRecordDTO.getThen().getStatusCode());
    }

    @Test
    public void test_process_rawData_is_null() {
        //Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        assertEquals(null, feedRecordDTO.getThen().getStatusCode());
    }

    @Test
    public void test_process_rawData_isEmpty() {
        //Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(" ", feedRecordDTO);

        // Then
        assertEquals(null, feedRecordDTO.getThen().getStatusCode());
    }

    @Test
    public void test_process_rawData_is_not_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setStatusCode(500);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("201", feedRecordDTO);

        // Then
        assertEquals(201, feedRecordDTO.getThen().getStatusCode());
    }
}
