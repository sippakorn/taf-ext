package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.GivenBlockDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonInputDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GivenJsonValueDataProcessorTest extends BaseDataProcessorTest {
    private GivenJsonValueDataProcessor dataProcessor;

    @Before
    public void setup(){
        dataProcessor = new GivenJsonValueDataProcessor();
    }

    @Test
    public void test_process_given_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        String rawValue = "value";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getValue(),"value");
    }

    @Test
    public void test_process_given_value_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        JsonInputDTO jsonInputDTO = getJsonInputDTO();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        jsonInputDTO.setValue(null);
        givenBlockDTO.setJsonData(Arrays.asList(jsonInputDTO));
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "value";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getValue(),"value");
    }

    @Test
    public void test_process_given_jsonData_is_null(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        givenBlockDTO.setJsonData(null);
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "value";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getValue(),"value");
    }

    @Test
    public void test_process_given_jsonData_is_empty(){
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        givenBlockDTO.setJsonData(new ArrayList<>());
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "value";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getValue(),"value");
    }
}
