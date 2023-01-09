package com.sahaj.parkingmanager;

import com.sahaj.model.*;

import java.util.ArrayList;

public abstract class ParkingLot {
    protected int motorCycleSpots;
    protected int carSuvSpots;
    protected int busTruckSpots;
    protected int nextReceiptNumber;
    protected int nextTicketNumber;

    protected ArrayList<Motorcycle> parkedMotorcycles;
    protected ArrayList<CarSuv> parkedCars;
    protected ArrayList<BusTruck> parkedBuses;

    public ParkingLot(int motorCycleSpots, int carSuvSpots,int busTruckSpots){

       this.setMotorCycleSpots(motorCycleSpots);
       this.setCarSuvSpots(carSuvSpots);
       this.setBusTruckSpots(busTruckSpots);
       nextReceiptNumber= 0;
       nextTicketNumber = 0;
    }

    protected  void setMotorCycleSpots(int motorCycleSpots)
    {
        if(motorCycleSpots < 0)
            throw  new IllegalArgumentException("Motorcycle spots cannot be negative");
        this.motorCycleSpots = motorCycleSpots;

    }

    protected  void setCarSuvSpots(int carSuvSpots)
    {
        if(carSuvSpots < 0)
            throw  new IllegalArgumentException("Motorcycle spots cannot be negative");
        this.carSuvSpots = carSuvSpots;
    }

    protected  void setBusTruckSpots(int busTruckSpots)
    {
        if(busTruckSpots < 0)
            throw  new IllegalArgumentException("Motorcycle spots cannot be negative");
        this.busTruckSpots = busTruckSpots;
    }
}
