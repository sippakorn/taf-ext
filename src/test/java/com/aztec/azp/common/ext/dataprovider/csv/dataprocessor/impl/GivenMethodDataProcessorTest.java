package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.ModificationMethod;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.GivenBlockDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonInputDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GivenMethodDataProcessorTest extends BaseDataProcessorTest {

    private GivenMethodDataProcessor dataProcessor;

    @Before
    public void setup() {
        dataProcessor = new GivenMethodDataProcessor();
    }

    @Test
    public void test_process_given_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        String rawValue = "UPDATE";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getMethod(), ModificationMethod.UPDATE);
    }

    @Test
    public void test_process_rawData_is_delete() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        String rawValue = "DELETE";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getMethod(), ModificationMethod.DELETE);
    }

    @Test
    public void test_process_rawData_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getMethod(), ModificationMethod.NO_ACTION);
    }

    @Test
    public void test_process_given_method_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        JsonInputDTO jsonInputDTO = getJsonInputDTO();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        jsonInputDTO.setMethod(null);
        givenBlockDTO.setJsonData(Arrays.asList(jsonInputDTO));
        feedRecordDTO.setGiven(givenBlockDTO);

        // When
        dataProcessor.process(null, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getMethod(), ModificationMethod.NO_ACTION);
    }

    @Test
    public void test_process_given_jsonData_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        givenBlockDTO.setJsonData(null);
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "UPDATE";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getMethod(), ModificationMethod.UPDATE);
    }

    @Test
    public void test_process_given_jsonData_is_empty() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        givenBlockDTO.setJsonData(new ArrayList<>());
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "DELETE";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(feedRecordDTO.getGiven().getJsonData().get(0).getMethod(), ModificationMethod.DELETE);
    }
}
