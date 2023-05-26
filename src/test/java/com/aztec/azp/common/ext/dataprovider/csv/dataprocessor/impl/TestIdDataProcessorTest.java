package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestIdDataProcessorTest extends BaseDataProcessorTest {
    private TestIdDataProcessor dataProcessor;

    @Before
    public void setup(){
        dataProcessor = new TestIdDataProcessor();
    }

    @Test(expected = NullPointerException.class)
    public void test_process_rawData_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        // expect NPE
    }

    @Test
    public void test_process_rawData_is_valid_and_is_not_AND(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("test", feedRecordDTO);

        // Then
        assertEquals("test", feedRecordDTO.getTestId());
    }

    @Test
    public void test_process_rawData_is_valid_and_is_Empty(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(" ", feedRecordDTO);

        // Then
        assertNull(feedRecordDTO.getTestId());
    }

    @Test
    public void test_process_rawData_is_valid_and_is__AND_(){
        // Given
        FeedRecordDTO feedRecordDTO = new FeedRecordDTO();

        // When
        dataProcessor.process(" AND ", feedRecordDTO);

        // Then
        assertNull(feedRecordDTO.getTestId());
    }

    @Test
    public void test_process_rawData_is_valid_and_is_and(){
        // Given
        FeedRecordDTO feedRecordDTO = new FeedRecordDTO();

        // When
        dataProcessor.process("and", feedRecordDTO);

        // Then
        assertNull(feedRecordDTO.getTestId());
    }
}
