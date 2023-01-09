package com.sahaj.model;

public class Vehicle {
    protected   ParkingReceipt receipt;
    protected   ParkingTicket ticket;
    protected   String plateNumber;

    public Vehicle(String plateNumber,ParkingTicket parkingTicket){
        if(plateNumber == null || plateNumber.equals(""))
        {
            throw new IllegalArgumentException("Platenumber cannot be empty");
        }

        ticket = parkingTicket;

    }

    public ParkingReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(ParkingReceipt receipt) {
        this.receipt = receipt;
    }

    public ParkingTicket getTicket() {
        return ticket;
    }

    public void setTicket(ParkingTicket ticket) {
        this.ticket = ticket;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

}
