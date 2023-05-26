package com.aztec.azp.common.ext.dataprovider;

import com.azp.exceptions.AZPException;
import com.aztec.api.rest.client.ExpectedData;
import com.aztec.api.rest.client.JsonExpectedData;
import com.aztec.api.rest.client.RestRequestBody;
import com.aztec.api.rest.client.CsvRestTestConfig;
import com.aztec.api.rest.client.XmlExpectedData;
import com.aztec.azp.common.annotation.TestInfoAzpLocal;
import com.aztec.azp.common.dataprovider.RestRequestDataProvider;
import com.aztec.azp.common.executions.TestExecutionManager;
import com.aztec.azp.common.ext.annotation.TestSelector;
import com.aztec.azp.common.ext.dataprovider.csv.ColumnData;
import com.aztec.azp.common.ext.dataprovider.csv.ColumnName;
import com.aztec.azp.common.ext.dataprovider.csv.CsvColumnConstants;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.ModificationMethod;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.GivenJsonPathDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.GivenJsonTypeDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.GivenJsonValueDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.GivenMethodDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.NotImplementDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.StringDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.TestIdDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.ThenJsonIsExistDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.ThenJsonSelectorDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.ThenStatusCodeDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.ThenXmlIsExistDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl.ThenXmlSelectorDataProcessor;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonInputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.JsonOutputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.FeedRecordDTO;
import com.aztec.azp.common.ext.dataprovider.csv.feed.XmlOutputDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.FilterDataDTO;
import com.aztec.azp.common.ext.dataprovider.csv.filter.ITestDataFilter;
import com.aztec.azp.common.ext.dataprovider.csv.filter.TestDataFilterFactory;
import com.aztec.azp.utils.AzpFileUtils;
import com.aztec.common.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvRestRequestDataProvider extends RestRequestDataProvider {
    private static String systemUnderTest;
    private static int startIndex = -1;
    private static int endIndex = -1;
    private static String[] testIds;
    private static double randomPercentExecute;

    public CsvRestRequestDataProvider() {
    }

    @DataProvider(
            name = "ReverseEngineering",
            parallel = true
    )
    public static synchronized Object[][] getData(Method method, ITestContext context) throws Exception {
        processAnnotation(method, context);
        processData(method, context);
        return returnData(method, context);
    }

    private static String retrieveFileLocation(Method method, ITestContext context, String datasourceType) throws AZPException {
        Class<?> clazz = method.getDeclaringClass();
        List<String> packages = Arrays.asList(clazz.getPackage().getName().split("\\.")).stream().map((p) ->
                p.toLowerCase()
        ).collect(Collectors.toList());
        String env = Config.getEnvironmentName().toString();
        String fileName = getFileNameByDataSourceType(method, datasourceType);
        String fileLocation = getDataDirectory(method, datasourceType, env, packages) + File.separator + fileName;
        logger.debug("fileLocation " + fileLocation + " for " + datasourceType);
        return fileLocation;
    }

    private static String getDataDirectory(Method method, String datasourceType, String environment, List<String> packages) {
        if (!datasourceType.equals("testData") && !datasourceType.equals("csvTestData") && !datasourceType.equals("testData_global")) {
            return "data-" + environment + File.separator + systemUnderTest + "/_goldenData";
        } else if (datasourceType.equals("csvTestData")) {
            Class<?> clazz = method.getDeclaringClass();
            String packagePath = packages.stream().collect(Collectors.joining(File.separator));
            return "data-" + environment + File.separator + systemUnderTest + File.separator + packagePath + File.separator + clazz.getSimpleName();
        } else {
            String packagePath = packages.stream().collect(Collectors.joining(File.separator));
            return "data-" + environment + File.separator + systemUnderTest + File.separator + packagePath;
        }
    }

    private static String getFileNameByDataSourceType(Method method, String datasourceType) throws AZPException {
        Class<?> clazz = method.getDeclaringClass();
        String className = clazz.getSimpleName();
        String methodName = method.getName();
        String goldenDataReference = (method.getAnnotation(TestInfoAzpLocal.class)).goldenData();
        if (datasourceType.equals("csvTestData")) {
            return methodName + ".csv";
        } else if (datasourceType.equals("testData")) {
            return className + ".json";
        } else if (datasourceType.equals("testData_global")) {
            return className + "_global.json";
        } else if (goldenDataReference != null && !goldenDataReference.equals("") && datasourceType.equals("goldenData")) {
            return "goldenData_" + goldenDataReference + ".json";
        } else if (goldenDataReference != null && !goldenDataReference.equals("") && datasourceType.equals("goldenData_parameterized")) {
            return "goldenData_" + goldenDataReference + "_parameterized.json";
        } else {
            throw new AZPException("The data source type needs is incorrect and/or golden data is not set properly.");
        }
    }

    private static void processAnnotation(Method currentTestMethod, ITestContext context) {
        ITestNGMethod[] iTestNGMethods = context.getAllTestMethods();
        for (ITestNGMethod iTestNGMethod : iTestNGMethods) {
            Method method = iTestNGMethod.getConstructorOrMethod().getMethod();
            if (method.getName().equalsIgnoreCase(currentTestMethod.getName())) {
                if (method.isAnnotationPresent(TestSelector.class)) {
                    TestSelector indexes = method.getAnnotation(TestSelector.class);
                    startIndex = indexes.startIndex();
                    endIndex = indexes.endIndex();
                    testIds = indexes.testIds();
                    randomPercentExecute = indexes.randomPercentExecute();
                }
            }
        }
    }

    protected static Object[][] returnData(Method method, ITestContext context) throws Exception {
        try {
            systemUnderTest = TestExecutionManager.getInstance().getExecutionInfoHolder(context).getExecutionInput().getApplicationId().getId().toLowerCase();
        } catch (Exception var11) {
            logger.error("No application context provided in test input.");
        }
        String splitBy = "\\|";
        List<FeedRecordDTO> testDatalist = new ArrayList<>();

        try {
            //parsing a CSV file into BufferedReader class constructor
            InputStream is = new ByteArrayInputStream(AzpFileUtils.loadBinaryContentFromResources(retrieveFileLocation(method, context, "csvTestData")));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            if ((line = br.readLine()) != null) { // read first line and process it metadata
                String[] columns = line.split(splitBy);
                List<ColumnData> columnDataList = parseColumns(columns);

                while ((line = br.readLine()) != null) {
                    String[] rowData = preProcess(columnDataList, line.split(splitBy));

                    ColumnData skipColumn = getColumn(columnDataList, ColumnName.SKIP);
                    if (isSkipRow(rowData[skipColumn.getIndex()])) {
                        continue;
                    }

                    // get test id column and use it to check multiple line
                    ColumnData testIdColumn = getColumn(columnDataList, ColumnName.TEST_ID);
                    if (isMultiLineData(rowData[testIdColumn.getIndex()])) {
                        FeedRecordDTO inputDTO = new FeedRecordDTO();
                        columnDataList.forEach(columnData -> columnData.process(rowData[columnData.getIndex()], inputDTO));
                        mergeRows(testDatalist.get(testDatalist.size() - 1), inputDTO);
                    } else {
                        FeedRecordDTO inputDTO = new FeedRecordDTO();
                        columnDataList.forEach(columnData -> columnData.process(rowData[columnData.getIndex()], inputDTO));
                        testDatalist.add(inputDTO);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("No test data file found. Using Default RestJson to execute test.");
            if (e instanceof FileNotFoundException) {
                testDatalist.add(null);
            }
        }

        FilterDataDTO filterData = new FilterDataDTO(startIndex, endIndex, testIds, randomPercentExecute);

        filterTestData(testDatalist, filterData);

        result = new Object[testDatalist.size()][1];

        if (testDatalist.size() > 0) {
            for (int r = 0; r < testDatalist.size(); ++r) {
                ObjectMapper objectMapper = new ObjectMapper();
                CsvRestTestConfig restTestConfig = objectMapper.readValue((data_result.get(0)).toJSONString(), CsvRestTestConfig.class);
                restTestConfig.sortRestRequestDataByOrder();
                assignFeedDataToTestConfig(restTestConfig, testDatalist.get(r));
                result[r][0] = restTestConfig;
                logger.debug("adding data set:" + restTestConfig.getProperties().getId() + "-" + restTestConfig.getProperties().getId());
            }
        }

        logger.debug("returning result with length " + result.length);
        return result;
    }

    private static String[] preProcess(List<ColumnData> columnDataList, String[] rowData) {
        String[] returnData = new String[columnDataList.size()];
        for (int i = 0; i < rowData.length; i++) {
            returnData[i] = rowData[i];
        }
        return returnData;
    }

    private static boolean isMultiLineData(String keyword) {
        if (keyword == null) {
            return false;
        }

        if (keyword.equalsIgnoreCase(CsvColumnConstants.AND)) {
            return true;
        }
        return false;
    }

    private static boolean isSkipRow(String keyword) {
        if (keyword == null) {
            return false;
        }

        if (CsvColumnConstants.TRUE_LIST.contains(keyword.toUpperCase())) {
            return true;
        }
        return false;
    }

    private static void filterTestData(List<FeedRecordDTO> data, FilterDataDTO filterData) {
        TestDataFilterFactory filterFactory = new TestDataFilterFactory();
        ITestDataFilter filterer = filterFactory.getFilter(filterData);

        filterer.filter(data, filterData);
    }

    private static void assignFeedDataToTestConfig(CsvRestTestConfig restTestConfig, FeedRecordDTO feedData) {
        if (feedData == null) {
            return;
        }

        assignData(restTestConfig, feedData);
        assignExpectedData(restTestConfig, feedData);
    }

    private static void assignData(CsvRestTestConfig restTestConfig, FeedRecordDTO feedData) {
        if (restTestConfig.getData() == null && restTestConfig.getData().getBody() == null) {
            return;
        }

        restTestConfig.getProperties().setDescription(feedData.getMessage());
        restTestConfig.getProperties().setId(feedData.getTestId());

        // assign data go here
        DocumentContext parsedDataContext = JsonPath.parse(restTestConfig.getData().getBody().getJSONObject());

        for (JsonInputDTO jsonInput : feedData.getGiven().getJsonData()) {
            if (jsonInput.getMethod() == ModificationMethod.UPDATE) {
                updateJson(parsedDataContext, jsonInput);
            } else if (jsonInput.getMethod() == ModificationMethod.DELETE) {
                deleteJson(parsedDataContext, jsonInput);
            } else {
                continue;
            }
        }

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonUpdated = (JSONObject) parser.parse(parsedDataContext.jsonString());
            restTestConfig.getData().setBody(new RestRequestBody(jsonUpdated));
        } catch (Exception ex) {
            logger.error("cannot update test data json");
        }
    }

    private static void assignExpectedData(CsvRestTestConfig restTestConfig, FeedRecordDTO feedData) {
        ExpectedData expectedData = new ExpectedData();
        expectedData.setTestMessage(feedData.getMessage());
        expectedData.setTestId(feedData.getTestId());
        expectedData.setStatusCode(feedData.getThen().getStatusCode());

        List<JsonExpectedData> jsonList = new ArrayList<>();
        feedData.getThen().getJsonData().forEach(json -> {
            JsonExpectedData jsonData = new JsonExpectedData();
            jsonData.setExist(json.isExist());
            jsonData.setValue(json.getSelector());
            jsonList.add(jsonData);
        });
        expectedData.setJsonData(jsonList);

        List<XmlExpectedData> xmlList = new ArrayList<>();
        feedData.getThen().getXmlData().forEach(xml -> {
            XmlExpectedData jsonData = new XmlExpectedData();
            jsonData.setExist(xml.isExist());
            String formatData = xml.getSelector();
            formatData = formatData.replace("\"\"", "\"");
            if (formatData.startsWith("\"")) {
                formatData = formatData.substring(1, formatData.length() - 1);
            }
            jsonData.setValue(formatData);
            xmlList.add(jsonData);
        });
        expectedData.setXmlData(xmlList);

        restTestConfig.setExpectedData(expectedData);
    }

    private static void mergeRows(FeedRecordDTO destination, FeedRecordDTO source) {
        // merge given
        if (destination.getGiven() == null || destination.getGiven().getJsonData() == null) {
            logger.error("Fail to parse test data: 2 rows of test data cannot merge due to empty GIVEN destination");
            return;
        }
        if (source.getGiven() == null || source.getGiven().getJsonData() == null) {
            logger.error("Fail to parse test data: 2 rows of test data cannot merge due to empty GIVEN source");
            return;
        }
        ArrayList<JsonInputDTO> temp = new ArrayList(destination.getGiven().getJsonData());
        source.getGiven().getJsonData().forEach(jsonData -> {
            if (jsonData.getMethod() != ModificationMethod.NO_ACTION) {
                temp.add(jsonData);
            }
        });
        destination.getGiven().setJsonData(temp);

        // merge then
        if (destination.getThen() == null) {
            logger.error("Fail to parse test data: 2 rows of test data cannot merge due to empty THEN destination");
            return;
        }

        if (source.getThen() == null) {
            logger.error("Fail to parse test data: 2 rows of test data cannot merge due to empty THEN source");
            return;
        }

        if (source.getThen().getJsonData() != null && destination.getThen().getJsonData() != null) {
            ArrayList<JsonOutputDTO> tempJson = new ArrayList<>(destination.getThen().getJsonData());
            source.getThen().getJsonData().forEach(jsonOutputDTO -> {
                if (jsonOutputDTO.isExist() != null) {
                    tempJson.add(jsonOutputDTO);
                }
            });
            destination.getThen().setJsonData(tempJson);
        }
        if (source.getThen().getXmlData() != null && destination.getThen().getXmlData() != null) {
            ArrayList<XmlOutputDTO> tempXml = new ArrayList<>(destination.getThen().getXmlData());
            source.getThen().getXmlData().forEach(xmlOutputDTO -> {
                if (xmlOutputDTO.isExist() != null) {
                    tempXml.add(xmlOutputDTO);
                }
            });
            destination.getThen().setXmlData(tempXml);
        }
    }

    private static List<ColumnData> parseColumns(String[] columns) {
        List<ColumnData> columnList = new ArrayList<>();
        ColumnData skipColumn = new ColumnData(ColumnName.SKIP, columns.length, new NotImplementDataProcessor());
        columnList.add(skipColumn);

        for (int index = 0; index < columns.length; index++) {
            if (columns[index] == null && columns[index].isEmpty()) {
                continue;
            }
            if (CsvColumnConstants.TEST_ID.equalsIgnoreCase(columns[index].toLowerCase())) {
                columnList.add(new ColumnData(ColumnName.TEST_ID, index, new TestIdDataProcessor()));
            } else if (CsvColumnConstants.MESSAGE.equalsIgnoreCase(columns[index].toLowerCase())) {
                columnList.add(new ColumnData(ColumnName.TEST_MESSAGE, index, new StringDataProcessor("Message")));
            } else if (CsvColumnConstants.DOCUMENT.equalsIgnoreCase(columns[index].toLowerCase())) {
                columnList.add(new ColumnData(ColumnName.DOCUMENT, index, new StringDataProcessor("Document")));
            } else if (columns[index].toLowerCase().trim().startsWith(CsvColumnConstants.GIVEN.toLowerCase())) {
                if (columns[index].toLowerCase().contains(CsvColumnConstants.METHOD.toLowerCase())) {
                    columnList.add(new ColumnData(ColumnName.GIVEN_METHOD, index, new GivenMethodDataProcessor()));
                } else if (columns[index].toLowerCase().contains(CsvColumnConstants.JSON_PATH.toLowerCase())) {
                    columnList.add(new ColumnData(ColumnName.GIVEN_JSON_PATH, index, new GivenJsonPathDataProcessor()));
                } else if (columns[index].toLowerCase().contains(CsvColumnConstants.VALUE.toLowerCase())) {
                    columnList.add(new ColumnData(ColumnName.GIVEN_JSON_VALUE, index, new GivenJsonValueDataProcessor()));
                } else if (columns[index].toLowerCase().contains(CsvColumnConstants.TYPE.toLowerCase())) {
                    columnList.add(new ColumnData(ColumnName.GIVEN_JSON_TYPE, index, new GivenJsonTypeDataProcessor()));
                }
            } else if (columns[index].toLowerCase().trim().startsWith(CsvColumnConstants.THEN.toLowerCase())) {
                if (columns[index].toLowerCase().contains(CsvColumnConstants.XML.toLowerCase())) {
                    if (columns[index].toLowerCase().contains(CsvColumnConstants.SELECTOR.toLowerCase())) {
                        columnList.add(new ColumnData(ColumnName.THEN_XML_SELECTOR, index, new ThenXmlSelectorDataProcessor()));
                    } else if (columns[index].toLowerCase().contains(CsvColumnConstants.IS_EXISTED.toLowerCase())) {
                        columnList.add(new ColumnData(ColumnName.THEN_XML_IS_EXIST, index, new ThenXmlIsExistDataProcessor()));
                    }
                } else if (columns[index].toLowerCase().contains(CsvColumnConstants.JSON.toLowerCase())) {
                    if (columns[index].toLowerCase().contains(CsvColumnConstants.SELECTOR.toLowerCase())) {
                        columnList.add(new ColumnData(ColumnName.THEN_JSON_SELECTOR, index, new ThenJsonSelectorDataProcessor()));
                    } else if (columns[index].toLowerCase().contains(CsvColumnConstants.IS_EXISTED.toLowerCase())) {
                        columnList.add(new ColumnData(ColumnName.THEN_JSON_IS_EXIST, index, new ThenJsonIsExistDataProcessor()));
                    }
                } else if (columns[index].toLowerCase().contains(CsvColumnConstants.STATUS_CODE.toLowerCase())) {
                    columnList.add(new ColumnData(ColumnName.THEN_STATUS_CODE, index, new ThenStatusCodeDataProcessor()));
                }
            } else if (columns[index].toLowerCase().trim().startsWith(CsvColumnConstants.SKIP.toLowerCase())) {
                columnList.remove(skipColumn);
                columnList.add(new ColumnData(ColumnName.SKIP, index, new NotImplementDataProcessor()));
            }
        }
        return columnList;
    }

    private static ColumnData getColumn(List<ColumnData> columnDataList, ColumnName columnName) {
        for (ColumnData columnData : columnDataList) {
            if (columnData.getColumnName() == columnName) {
                return columnData;
            }
        }
        return null;
    }

    private static void updateJson(DocumentContext parsedDataContext, JsonInputDTO jsonInput) {
        if (jsonInput.getValue() == null) {
            return;
        }
        switch (jsonInput.getType()) {
            case BOOLEAN:
                parsedDataContext.set(jsonInput.getPath(), Boolean.parseBoolean(jsonInput.getValue()));
                break;
            case NULL:
                parsedDataContext.set(jsonInput.getPath(), null);
                break;
            case OBJECT:
                try {
                    JSONParser parser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) parser.parse(jsonInput.getValue());
                    parsedDataContext.set(jsonInput.getPath(), jsonObject);
                } catch (ParseException ex) {
                    logger.error("cannot parse json object");
                }
                break;
            case STRING:
            default:
                String rawString = jsonInput.getValue();
                rawString = rawString.replace("\"\"\"", "");
                rawString = rawString.replace("\"\"", "");
                parsedDataContext.set(jsonInput.getPath(), rawString);

        }

    }

    private static void deleteJson(DocumentContext parsedDataContext, JsonInputDTO jsonInput) {
        parsedDataContext.delete(jsonInput.getPath());
    }

}
