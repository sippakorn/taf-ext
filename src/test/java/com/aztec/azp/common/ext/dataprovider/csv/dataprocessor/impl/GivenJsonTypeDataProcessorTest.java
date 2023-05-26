package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.GivenBlockDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonInputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonNodeType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GivenJsonTypeDataProcessorTest extends BaseDataProcessorTest {
    private GivenJsonTypeDataProcessor dataProcessor;

    @Before
    public void setup() {
        dataProcessor = new GivenJsonTypeDataProcessor();
    }

    @Test
    public void test_process_rawData_is_null() {
        // Given
        FeedRecordDTO actualDTO = new FeedRecordDTO();

        // When
        dataProcessor.process(null, actualDTO);

        // Then
        assertEquals(JsonNodeType.STRING, actualDTO.getGiven().getJsonData().get(0).getType());

    }

    @Test
    public void test_process_rawData_is_empty() {
        // Given
        FeedRecordDTO actualDTO = new FeedRecordDTO();
        GivenBlockDTO givenBlockDTO = new GivenBlockDTO();
        actualDTO.setGiven(givenBlockDTO);

        // When
        dataProcessor.process("", actualDTO);

        // Then
        assertEquals(JsonNodeType.STRING, actualDTO.getGiven().getJsonData().get(0).getType());

    }

    @Test
    public void test_process_rawData_is_invalid_type() {
        // Given
        String rawData = "VALUE";
        FeedRecordDTO actualDTO = new FeedRecordDTO();

        // When
        dataProcessor.process(rawData, actualDTO);

        // Then
        assertEquals(JsonNodeType.STRING, actualDTO.getGiven().getJsonData().get(0).getType());

    }

    @Test
    public void test_process_rawData_is_valid_type() {
        // Given
        String rawData = "BOOLEAN";
        FeedRecordDTO actualDTO = new FeedRecordDTO();

        // When
        dataProcessor.process(rawData, actualDTO);

        // Then
        assertEquals(JsonNodeType.BOOLEAN, actualDTO.getGiven().getJsonData().get(0).getType());

    }

    //region givenBlockDTO is valid
    @Test
    public void test_process_rawData_is_null_and_givenBlockDTO_is_valid() {
        // Given
        FeedRecordDTO actualDTO = new FeedRecordDTO();
        actualDTO.setGiven(getValidGivenBlockDTO());

        // When
        dataProcessor.process(null, actualDTO);

        // Then
        assertNotNull(actualDTO.getGiven().getJsonData());
        assertEquals(JsonNodeType.STRING, actualDTO.getGiven().getJsonData().get(0).getType());
    }

    @Test
    public void test_process_rawData_is_empty_and_givenBlockDTO_is_valid() {
        // Given
        FeedRecordDTO actualDTO = new FeedRecordDTO();
        actualDTO.setGiven(getValidGivenBlockDTO());

        // When
        dataProcessor.process("", actualDTO);

        // Then
        assertNotNull(actualDTO.getGiven().getJsonData());
        assertEquals(JsonNodeType.STRING, actualDTO.getGiven().getJsonData().get(0).getType());
    }

    @Test
    public void test_process_rawData_is_invalid_type_and_givenBlockDTO_is_valid() {
        // Given
        FeedRecordDTO actualDTO = new FeedRecordDTO();
        actualDTO.setGiven(getValidGivenBlockDTO());

        // When
        dataProcessor.process("VALUE", actualDTO);

        // Then
        assertNotNull(actualDTO.getGiven().getJsonData());
        assertEquals(JsonNodeType.STRING, actualDTO.getGiven().getJsonData().get(0).getType());
    }

    @Test
    public void test_process_rawData_is_valid_type_and_givenBlockDTO_is_valid() {
        // Given
        FeedRecordDTO actualDTO = new FeedRecordDTO();
        actualDTO.setGiven(getValidGivenBlockDTO());

        // When
        dataProcessor.process("BOOLEAN", actualDTO);

        // Then
        assertNotNull(actualDTO.getGiven().getJsonData());
        assertEquals(JsonNodeType.BOOLEAN, actualDTO.getGiven().getJsonData().get(0).getType());
    }
    //endregion


    //@Test
    public void test_process_given_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        String rawValue = "STRING";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(JsonNodeType.STRING, feedRecordDTO.getGiven().getJsonData().get(0).getType());
    }

    //@Test
    public void test_process_given_type_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        JsonInputDTO jsonInputDTO = getJsonInputDTO();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        jsonInputDTO.setPath(null);
        givenBlockDTO.setJsonData(Arrays.asList(jsonInputDTO));
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "BOOLEAN";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(JsonNodeType.BOOLEAN, feedRecordDTO.getGiven().getJsonData().get(0).getType());
    }

    //@Test
    public void test_process_given_type_is_object() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        JsonInputDTO jsonInputDTO = getJsonInputDTO();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        jsonInputDTO.setPath(null);
        givenBlockDTO.setJsonData(Arrays.asList(jsonInputDTO));
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "OBJECT";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(JsonNodeType.OBJECT, feedRecordDTO.getGiven().getJsonData().get(0).getType());
    }

    // @Test
    public void test_process_given_type_is_not_match() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        JsonInputDTO jsonInputDTO = getJsonInputDTO();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        jsonInputDTO.setPath(null);
        givenBlockDTO.setJsonData(Arrays.asList(jsonInputDTO));
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "MISSPELLING";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(JsonNodeType.STRING, feedRecordDTO.getGiven().getJsonData().get(0).getType());
    }

    // @Test
    public void test_process_given_jsonData_is_null() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        givenBlockDTO.setJsonData(null);
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "NUMBER";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(JsonNodeType.NUMBER, feedRecordDTO.getGiven().getJsonData().get(0).getType());
    }

    // @Test
    public void test_process_given_jsonData_is_empty() {
        // Given
        FeedRecordDTO feedRecordDTO = getFeedRecord();
        GivenBlockDTO givenBlockDTO = getGivenBlockDTO();
        givenBlockDTO.setJsonData(new ArrayList<>());
        feedRecordDTO.setGiven(givenBlockDTO);
        String rawValue = "NULL";

        // When
        dataProcessor.process(rawValue, feedRecordDTO);

        // Then
        assertNotNull(feedRecordDTO.getGiven());
        assertEquals(JsonNodeType.NULL, feedRecordDTO.getGiven().getJsonData().get(0).getType());
    }
}
