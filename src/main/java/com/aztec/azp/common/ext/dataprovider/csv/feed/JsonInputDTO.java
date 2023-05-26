package com.aztec.azp.common.ext.dataprovider.csv.feed;

import com.aztec.azp.common.ext.dataprovider.csv.dataprocessor.ModificationMethod;

public class JsonInputDTO {
    private ModificationMethod method;
    private String path;
    private JsonNodeType type;
    private String value;

    public JsonInputDTO() {
    }

    public ModificationMethod getMethod() {
        return method;
    }

    public void setMethod(ModificationMethod method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonNodeType getType() {
        return type;
    }

    public void setType(JsonNodeType type) {
        this.type = type;
    }
}
