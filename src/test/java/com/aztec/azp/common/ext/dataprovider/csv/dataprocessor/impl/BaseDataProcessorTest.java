package com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.impl;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.ModificationMethod;
import com.aztec.azp.common.ext.dataprovider.csv.feed.*;

import java.util.*;

public class BaseDataProcessorTest {

    public final static String[] trueStrings = new String[]
            {"TRUE", "True", "true", "T", "t", "1", "YES", "Yes", "yes", "Y", "y"};

    public final static String[] falseStrings = new String[]
            {"FALSE", "False", "false", "F", "f", "0", "NO", "No", "no", "N", "n"};

    public FeedRecordDTO getFeedRecord() {
        FeedRecordDTO feedRecordDTO = new FeedRecordDTO();
        feedRecordDTO.setTestId("TestId");
        feedRecordDTO.setMessage("Message");
        feedRecordDTO.setDisable(false);
        feedRecordDTO.setDocument("Document");
        feedRecordDTO.setGiven(null);
        feedRecordDTO.setThen(null);
        return feedRecordDTO;
    }

    public GivenBlockDTO getGivenBlockDTO() {
        return new GivenBlockDTO();
    }

    public GivenBlockDTO getValidGivenBlockDTO(){
        GivenBlockDTO givenBlockDTO = new GivenBlockDTO();
        ArrayList<JsonInputDTO> jsonInputDTOS = new ArrayList<>();
        jsonInputDTOS.add(getJsonInputDTO());
        givenBlockDTO.setJsonData(jsonInputDTOS);
        return givenBlockDTO;
    }

    public JsonInputDTO getJsonInputDTO() {
        JsonInputDTO jsonInputDTO = new JsonInputDTO();
        jsonInputDTO.setMethod(ModificationMethod.UPDATE);
        jsonInputDTO.setPath("test/path");
        jsonInputDTO.setType(JsonNodeType.STRING);
        jsonInputDTO.setValue("TestTest");

        return jsonInputDTO;
    }

    public ThenBlockDTO getThenBlockDTO() {
        ThenBlockDTO thenBlockDTO = new ThenBlockDTO();
        ArrayList<JsonOutputDTO> jsonOutputDTOS = new ArrayList<>();
        jsonOutputDTOS.add(getJsonOutputDTO());
        ArrayList<XmlOutputDTO> xmlOutputDTOS = new ArrayList<>();
        xmlOutputDTOS.add(getXmlOutputDTO());
        thenBlockDTO.setStatusCode(200);
        thenBlockDTO.setJsonData(jsonOutputDTOS);
        thenBlockDTO.setXmlData(xmlOutputDTOS);
        return thenBlockDTO;
    }

    public XmlOutputDTO getXmlOutputDTO() {
        XmlOutputDTO XmlOutputDTO = new XmlOutputDTO();
        XmlOutputDTO.setExist(true);
        XmlOutputDTO.setSelector("//xml/path");
        return XmlOutputDTO;
    }

    public JsonOutputDTO getJsonOutputDTO() {
        JsonOutputDTO jsonOutputDTO = new JsonOutputDTO();
        jsonOutputDTO.setExist(true);
        jsonOutputDTO.setSelector("$.json.path");
        return jsonOutputDTO;
    }

}
