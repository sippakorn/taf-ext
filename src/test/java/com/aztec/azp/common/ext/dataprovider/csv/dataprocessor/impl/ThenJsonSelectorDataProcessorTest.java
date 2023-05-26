package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.ThenBlockDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ThenJsonSelectorDataProcessorTest extends BaseDataProcessorTest {
    private ThenJsonSelectorDataProcessor dataProcessor;

    @Before
    public void setup(){
        dataProcessor = new ThenJsonSelectorDataProcessor();
    }

    //region Base Case
    @Test
    public void test_process_rawData_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(null,feedRecordDTO);

        // Then
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_is_empty(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("",feedRecordDTO);

        // Then
        assertEquals("", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_is_valid(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("path",feedRecordDTO);

        // Then
        assertEquals("path", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_has_space(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(" path ",feedRecordDTO);

        // Then
        assertEquals("path", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }
    //endregion
    //region Null JsonOutputDTO Case
    @Test
    public void test_process_rawData_is_null_and_jsonOutputDTO_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process(null,feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_is_empty_and_jsonOutputDTO_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("",feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals("", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_is_valid_and_jsonOutputDTO_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process("path",feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals("path", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_has_space_and_jsonOutputDTO_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        ThenBlockDTO thenBlockDTO = getThenBlockDTO();
        thenBlockDTO.setJsonData(null);
        feedRecordDTO.setThen(thenBlockDTO);

        // When
        dataProcessor.process(" path ",feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals("path", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }
    //endregion
    //region Valid JsonOutputDTO Case
    @Test
    public void test_process_rawData_is_null_and_jsonOutputDTO_is_valid(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process(null,feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(null, feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_is_empty_and_jsonOutputDTO_is_valid(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("",feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals("", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_is_valid_and_jsonOutputDTO_is_valid(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process("path",feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals("path", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }

    @Test
    public void test_process_rawData_has_space_and_jsonOutputDTO_is_valid(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        feedRecordDTO.setThen(getThenBlockDTO());

        // When
        dataProcessor.process(" path ",feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getThen().getJsonData());
        assertEquals(" path ", feedRecordDTO.getThen().getJsonData().get(0).getSelector());
    }
    //endregion
}
