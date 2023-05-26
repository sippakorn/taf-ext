package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringDataProcessorTest extends BaseDataProcessorTest {

    private StringDataProcessor dataProcessor;

    @Test
    public void test_process_setDocument() {
        // Given
        dataProcessor = new StringDataProcessor("Document");
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("test", feedRecordDTO);

        // Then
        assertEquals("test", feedRecordDTO.getDocument());
    }

    @Test
    public void test_process_setMessage() {
        // Given
        dataProcessor = new StringDataProcessor("Message");
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("test", feedRecordDTO);

        // Then
        assertEquals("test", feedRecordDTO.getMessage());
    }

    @Test
    public void test_process_setMissingField() {
        // Given
        dataProcessor = new StringDataProcessor("MissingField");
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("test", feedRecordDTO);

    }

    @Test
    public void test_process_setIncCorrectType() {
        // Given
        dataProcessor = new StringDataProcessor("Document");
        FeedRecordDTO feedRecordDTO = getFeedRecord();

        // When
        dataProcessor.process("test", feedRecordDTO);

    }
    
}

