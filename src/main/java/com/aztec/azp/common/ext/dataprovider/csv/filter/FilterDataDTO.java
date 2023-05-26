package com.aztec.azp.common.ext.dataprovider.csv.filter;

public class FilterDataDTO {
    private int startIndex;
    private int endIndex;
    private String[] testIds;
    private double randomPercentExecute;

    public FilterDataDTO(int startIndex, int endIndex, String[] testIds, double randomPercentExecute){
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.testIds = testIds;
        this.randomPercentExecute = randomPercentExecute;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String[] getTestIds() {
        return testIds;
    }

    public void setTestIds(String[] testIds) {
        this.testIds = testIds;
    }

    public double getRandomPercentExecute() {
        return randomPercentExecute;
    }

    public void setRandomPercentExecute(double randomPercentExecute) {
        this.randomPercentExecute = randomPercentExecute;
    }
}
