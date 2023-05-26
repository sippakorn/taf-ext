package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import org.junit.Before;
import org.junit.Test;

public class NotImplementDataProcessorTest extends BaseDataProcessorTest {

    private NotImplementDataProcessor dataProcessor;

    @Before
    public void setup() {
        dataProcessor = new NotImplementDataProcessor();
    }

    @Test
    public void test_process() {
        dataProcessor.process("foo", getFeedRecord());
    }
}
