package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.ThenBlockDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThenXmlIsExistDataProcessorTest extends BaseDataProcessorTest {
    private ThenXmlIsExistDataProcessor dataProcessor;

    @Before
    public void setup() {
        dataProcessor = new ThenXmlIsExistDataProcessor();
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
        assertEquals(null, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_empty() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(null, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_invalid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("falze", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(null, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_has_space() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(" true ", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(true, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }
    //endregion
    //region Null XmlOutputDTO Case
    @Test
    public void test_process_rawData_is_empty_and_xmlOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setXmlData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("", feedRecordDTO);

        // Then
        assertNull(feedRecordDTO.getThen());
    }

    @Test
    public void test_process_rawData_is_null_and_xmlOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setXmlData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        assertNull(feedRecordDTO.getThen());
    }

    @Test
    public void test_process_rawData_is_true_and_xmlOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setXmlData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("true", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(true, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_false_and_xmlOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setXmlData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("false", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(false, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_invalid_and_xmlOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setXmlData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("falze", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(false, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_has_space_and_xmlOutputDTO_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setXmlData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process(" true ", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen());
        assertEquals(true, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }
    //endregion
    //region Valid XmlOutputDTO Case
    @Test
    public void test_process_rawData_is_empty_and_xmlOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getXmlData());
        assertEquals(null, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_null_and_xmlOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getXmlData());
        assertEquals(null, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_true_and_xmlOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("true", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getXmlData());
        assertEquals(true, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_false_and_xmlOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("false", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getXmlData());
        assertEquals(false, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }

    @Test
    public void test_process_rawData_is_invalid_and_xmlOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("truez", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getXmlData());
        assertEquals(null, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }
    
    @Test
    public void test_process_rawData_has_space_and_xmlOutputDTO_is_valid() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process(" true ", feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getXmlData());
        assertEquals(true, feedRecordDTO.getThen().getXmlData().get(0).isExist());
    }
    //endregion
}
