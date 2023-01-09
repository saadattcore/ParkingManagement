package com.sahaj.model;

import  java.time.LocalDateTime;

public class ParkingTicket {

    private String ticketNumber;
    private int spotNumber;
    private LocalDateTime entryTime;

    public ParkingTicket(LocalDateTime entryTime) {
         this.entryTime = entryTime;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public String toString() {
      return   String.format("Parking Ticket: \n Ticket Number:%s \n Spot Number: %d \n Entry Date Time:%s",ticketNumber,spotNumber,entryTime);
    }
}
