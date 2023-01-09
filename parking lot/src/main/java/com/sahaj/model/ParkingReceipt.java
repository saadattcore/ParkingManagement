package com.sahaj.model;


import java.time.LocalDateTime;

public class ParkingReceipt {

    private String receiptNumber;
    private  LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private int fee;

    public ParkingReceipt(String receiptNumber, LocalDateTime entryDate, LocalDateTime exitDate, int fee) {
        this.receiptNumber = receiptNumber;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.fee = fee;
    }


    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }


    @Override
    public String toString() {
        return String.format("Parking Receipt: \n  Receipt Number:%s \n Entry Date-Time: %s \n Exit Date-Time:%s \n Fees:%d",receiptNumber,entryDate,exitDate,fee);
    }
}
