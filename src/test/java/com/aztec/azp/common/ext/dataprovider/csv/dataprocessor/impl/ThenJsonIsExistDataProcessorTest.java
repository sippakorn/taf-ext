package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.ModificationMethod;
import com.aztec.azp.common.ext.dataprovider.csv.feed.*;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ThenJsonIsExistDataProcessorTest extends BaseDataProcessorTest {
    private ThenJsonIsExistDataProcessor dataProcessor;

    @Before
    public void setup() {
        dataProcessor = new ThenJsonIsExistDataProcessor();
    }
    
    //region Base Case

    @Test
    public void test_process_rawData_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_empty() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_invalid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("falze", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_has_space() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(" true ", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(true, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    //endregion
    //region Null jsonOutputDTO Case
    @Test
    public void test_process_rawData_is_empty_and_jsonOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("", feedRecordDTO);

        // Then
        assertNull(feedRecordDTO.getThen());
    }

    @Test
    public void test_process_rawData_is_null_and_jsonOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        assertNull(feedRecordDTO.getThen());
    }

    @Test
    public void test_process_rawData_is_true_and_jsonOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("true", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(true, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_false_and_jsonOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("false", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(false, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_invalid_and_jsonOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("falze", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(false, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_has_space_and_jsonOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process(" true ", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(true, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    //endregion
    //region Valid jsonOutputDTO Case
    @Test
    public void test_process_rawData_is_empty_and_jsonOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_null_and_jsonOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_true_and_jsonOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("true", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(true, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_false_and_jsonOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("false", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(false, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_invalid_and_jsonOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("truez", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_has_space_and_jsonOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process(" true ", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(true, feedRecordDTO.getThen().getJsonData().get(0).isExist());
    }
    //endregion
}
