package com.aztec.azp.common.ext.dataprovider.csv;

import java.util.Arrays;
import java.util.List;

public class CsvColumnConstants {
    // Column Name Define Here

    // Column Name Matcher
    public static final String TEST_ID = "ID";
    public static final String MESSAGE = "Test Message";
    public static final String DOCUMENT = "Documentation";
    public static final String GIVEN = "Given:";
    public static final String THEN = "Then:";
    public static final String METHOD = "Method";
    public static final String JSON_PATH = "JsonPath";
    public static final String VALUE = "Value";
    public static final String TYPE = "Type";
    public static final String STATUS_CODE = "Status Code";
    public static final String IS_EXISTED = "isExisted";
    public static final String XML = "XML";
    public static final String JSON = "JSON";
    public static final String SELECTOR = "SELECTOR";
    public static final String AND = "AND";
    public static final String SKIP = "SKIP";

    // Column Constant Optional Values
    public static final List<String> FALSE_LIST
            = Arrays.asList("FALSE", "F", "0", "NO", "N");
    public static final List<String> TRUE_LIST
            = Arrays.asList("TRUE", "T", "1", "YES", "Y");
}
