package com.aztec.azp.common.ext.dataprovider;

import org.junit.Before;
import org.junit.Test;

import org.testng.ITestContext;
import org.testng.TestRunner;

import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;


public class CsvRestRequestDataProviderTest {
    private static ITestContext testContext;
    private static Method testMethod;

    @Before
    public void setUp() {
        testContext = mock(TestRunner.class);
        try {
            testMethod = SampleTestSuite.class.getMethod("testSampleCase");
        } catch (SecurityException e) {  }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Test
    public static void testGetData() throws Exception{
        CsvRestRequestDataProvider.getData(testMethod, testContext);
    }

}
