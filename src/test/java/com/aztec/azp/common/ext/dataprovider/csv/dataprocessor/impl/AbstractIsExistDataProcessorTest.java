package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class AbstractIsExistDataProcessorTest {
    class ImplementAbstractIsExistDataProcessor extends AbstractIsExistDataProcessor {

    }

    ImplementAbstractIsExistDataProcessor dataProcessor;

    @Before
    public void setup() {
        dataProcessor = new ImplementAbstractIsExistDataProcessor();
    }

    @Test
    public void test_parseIsExist_value_is_null() {
        assertNull(dataProcessor.parseIsExist(null));
    }

    @Test
    public void test_parseIsExist_value_is_empty() {
        assertNull(dataProcessor.parseIsExist(""));
    }

    @Test
    public void test_parseIsExist_value_is_true() {
        for (String trueString : BaseDataProcessorTest.trueStrings) {
            assertEquals(dataProcessor.parseIsExist(trueString), true);
        }
//        assertEquals(dataProcessor.parseIsExist("TRUE"), true);
//        assertEquals(dataProcessor.parseIsExist("true"), true);
//        assertEquals(dataProcessor.parseIsExist("T"), true);
//        assertEquals(dataProcessor.parseIsExist("1"), true);
//        assertEquals(dataProcessor.parseIsExist("Yes"), true);
//        assertEquals(dataProcessor.parseIsExist("Y"), true);
    }

    @Test
    public void test_parseIsExist_value_is_false() {
        for (String falseString : BaseDataProcessorTest.falseStrings) {
            assertEquals(dataProcessor.parseIsExist(falseString), false);
        }
//        assertEquals(dataProcessor.parseIsExist("FALSE"), false);
//        assertEquals(dataProcessor.parseIsExist("false"), false);
//        assertEquals(dataProcessor.parseIsExist("F"), false);
//        assertEquals(dataProcessor.parseIsExist("0"), false);
//        assertEquals(dataProcessor.parseIsExist("No"), false);
//        assertEquals(dataProcessor.parseIsExist("N"), false);
    }
}
