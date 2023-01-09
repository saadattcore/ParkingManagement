package com.sahaj.parkingmanager;

import com.sahaj.interfaces.*;
import com.sahaj.model.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AirportParkingLot extends ParkingLot implements AirportParkingInterface {

    private  Hashtable<String,ArrayList<HourRange>> feeStructure;
    private final int INFINITE = 9999;

    public AirportParkingLot(int motorCycleSpots, int carSuvSpots)
    {
        super(motorCycleSpots,carSuvSpots,0);

        parkedMotorcycles = new ArrayList<>(motorCycleSpots);
        parkedCars = new ArrayList<>(carSuvSpots);
        parkedBuses = new ArrayList<>(busTruckSpots);

        feeStructure = new Hashtable<String,ArrayList<HourRange>>();

        ArrayList<HourRange> motorcycleFees = new ArrayList<>();
        motorcycleFees.add(new HourRange(0,1,0));
        motorcycleFees.add(new HourRange(1,8,40));
        motorcycleFees.add(new HourRange(8,24,60));
        motorcycleFees.add(new HourRange(24,INFINITE,80));
        feeStructure.put("Motorcycle",motorcycleFees);

        ArrayList<HourRange> carFee = new ArrayList<>();
        carFee.add(new HourRange(0,12,60));
        carFee.add(new HourRange(12,24,80));
        carFee.add(new HourRange(24,INFINITE,100));
        feeStructure.put("Car",carFee);

    }


    @Override
    public ParkingTicket parkMotorcycle(Motorcycle motorcycle) throws Exception
    {
        if(motorcycle == null || motorcycle.getTicket() == null)
            throw new IllegalArgumentException("Motorcycle cannot be null");

        if(motorcycle.getTicket() == null)
            throw new IllegalArgumentException("Receipt cannot be null");

        if(parkedMotorcycles.size() == this.motorCycleSpots )
            throw  new IllegalArgumentException("Parking capacity for motorcycles is full");

        int availableSpot = parkedMotorcycles.size() > 0 ? parkedMotorcycles.size() : 1;

        String ticketNumber = String.format("%03d",++nextTicketNumber);

        ParkingTicket receipt =  motorcycle.getTicket();
        receipt.setTicketNumber(ticketNumber);
        receipt.setSpotNumber(availableSpot);

        parkedMotorcycles.add(motorcycle);

        return receipt;
    }

    @Override
    public ParkingReceipt unParkMotorcycle(String ticketNumber, LocalDateTime unparkTime) throws Exception
    {
        if(ticketNumber == null || ticketNumber.equals(""))
            throw new IllegalArgumentException("Receipt cannot be null");

       Motorcycle parkedMotorcycle =  parkedMotorcycles.stream()
                .filter(v -> v.getTicket().getTicketNumber().equals(ticketNumber))
                .findFirst()
                .get();

       if(parkedMotorcycle == null)
           throw new Exception(String.format("Vehicle with ticket %s not found",ticketNumber));

        int fee = 0;

        long parkedHours =  ChronoUnit.HOURS.between(parkedMotorcycle.getTicket().getEntryTime() , unparkTime);

        ArrayList<HourRange> motorCycleFee =  feeStructure.get("Motorcycle");

        HourRange inifite = motorCycleFee.stream()
                .filter(h-> h.getUpperLimit() == INFINITE)
                .findFirst().get();

        if(parkedHours < inifite.getLowerLimit()){

          fee =   motorCycleFee.stream()
                    .filter(h -> parkedHours >= h.getLowerLimit() && parkedHours <h.getUpperLimit() )
                    .findFirst()
                    .get().getFee();

        }else
        {
            int days = (int)Math.ceil((double) parkedHours / 24);
            fee = inifite.getFee() * days;
        }

        ParkingReceipt r = new ParkingReceipt(String.format("R-%03d",++nextReceiptNumber),
                parkedMotorcycle.getTicket().getEntryTime(),
                unparkTime,fee
        );

        parkedMotorcycle.setReceipt(r);

        return parkedMotorcycle.getReceipt();
    }

    @Override
    public ParkingTicket parkCarSuv(CarSuv car) {
        if(car == null || car.getTicket() == null)
            throw new IllegalArgumentException("car cannot be null");

        if(car.getTicket() == null)
            throw new IllegalArgumentException("Receipt cannot be null");

        if(parkedCars.size() == this.carSuvSpots )
            throw  new IllegalArgumentException("Parking capacity for motorcycles is full");

        int availableSpot = parkedCars.size() > 0 ? parkedCars.size() : 1;

        String ticketNumber = String.format("%03d",++nextTicketNumber);

        ParkingTicket receipt =  car.getTicket();
        receipt.setTicketNumber(ticketNumber);
        receipt.setSpotNumber(availableSpot);

        parkedCars.add(car);

        return receipt;
    }

    @Override
    public ParkingReceipt unParkCarSuv(String ticketNumber, LocalDateTime unparkTime) throws Exception {

        if(ticketNumber == null || ticketNumber.equals(""))
            throw new IllegalArgumentException("Receipt cannot be null");

        CarSuv parkedCar =  parkedCars.stream()
                .filter(v -> v.getTicket().getTicketNumber().equals(ticketNumber))
                .findFirst()
                .get();

        if(parkedCar == null)
            throw new Exception(String.format("Vehicle with ticket %s not found",ticketNumber));

        int fee = 0;

        long parkedHours =  ChronoUnit.HOURS.between(parkedCar.getTicket().getEntryTime() , unparkTime);

        ArrayList<HourRange> carSuvFee =  feeStructure.get("Car");

        HourRange inifite = carSuvFee.stream()
                .filter(h-> h.getUpperLimit() == INFINITE)
                .findFirst().get();

        if(parkedHours < inifite.getLowerLimit()){

            fee =   carSuvFee.stream()
                    .filter(h -> parkedHours >= h.getLowerLimit() && parkedHours <h.getUpperLimit() )
                    .findFirst()
                    .get().getFee();

        }else
        {
            int days = (int)Math.ceil((double) parkedHours / 24);
            fee = inifite.getFee() * days;
        }

        ParkingReceipt r = new ParkingReceipt(String.format("R-%03d",++nextReceiptNumber),
                parkedCar.getTicket().getEntryTime(),
                unparkTime,fee
        );

        parkedCar.setReceipt(r);

        return parkedCar.getReceipt();
    }


}

