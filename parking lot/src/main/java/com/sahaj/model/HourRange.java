package com.sahaj.model;

public class HourRange {

    private int lowerLimit;
    private int upperLimit;
    private int fee;



    public HourRange(int lowerLimit, int upperLimit, int fee)
    {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.fee = fee;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }


}
