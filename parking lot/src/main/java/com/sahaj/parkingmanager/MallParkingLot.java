package com.sahaj.parkingmanager;

import com.sahaj.interfaces.*;
import com.sahaj.model.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MallParkingLot extends ParkingLot implements MallParkingInterface {

    private Hashtable<String,Integer> feeStructure;

    public MallParkingLot(int motorCycleSpots, int carSuvSpots, int busTruckSpots)
    {
        super(motorCycleSpots,carSuvSpots,busTruckSpots);

        parkedMotorcycles = new ArrayList<>(motorCycleSpots);
        parkedCars = new ArrayList<>(carSuvSpots);
        parkedBuses = new ArrayList<>(busTruckSpots);

        feeStructure = new Hashtable<String,Integer>();
        feeStructure.put("Motorcycle",10);
        feeStructure.put("CarSuv",20);
        feeStructure.put("BusTruck",50);

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

        parkedMotorcycles.remove(parkedMotorcycle);

        if(parkedMotorcycle == null)
            throw new Exception(String.format("Vehicle with ticket %s not found",ticketNumber));

        int fee = 0;

        long parkedMinutes =  ChronoUnit.MINUTES.between(parkedMotorcycle.getTicket().getEntryTime() , unparkTime);
        double parkedHours = (double) parkedMinutes / 60;

       int motorcycleFee =  feeStructure.get("Motorcycle");
       fee = (int)Math.ceil(parkedHours) * motorcycleFee;


       ParkingReceipt r = new ParkingReceipt(String.format("R-%03d", ++nextReceiptNumber),
               parkedMotorcycle.getTicket().getEntryTime()
               ,unparkTime,fee);

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


        parkedCars.remove(parkedCar);

        if(parkedCar == null)
            throw new Exception(String.format("Vehicle with ticket %s not found",ticketNumber));

        int fee = 0;

        long parkedMinutes =  ChronoUnit.MINUTES.between(parkedCar.getTicket().getEntryTime() , unparkTime);
        double parkedHours = (double) parkedMinutes / 60;

        fee = (int)Math.ceil(parkedHours) * feeStructure.get("CarSuv");

        ParkingReceipt r = new ParkingReceipt(String.format("R-%03d", ++nextReceiptNumber),
                parkedCar.getTicket().getEntryTime()
                ,unparkTime,fee);

        parkedCar.setReceipt(r);

        return parkedCar.getReceipt();
    }
    @Override
    public ParkingTicket parkBusTruck(BusTruck busTruck) throws Exception {
        if(busTruck == null || busTruck.getTicket() == null)
            throw new IllegalArgumentException("car cannot be null");

        if(busTruck.getTicket() == null)
            throw new IllegalArgumentException("Receipt cannot be null");

        if(parkedBuses.size() == this.busTruckSpots )
            throw  new IllegalArgumentException("Parking capacity for motorcycles is full");

        int availableSpot = parkedBuses.size() > 0 ? parkedBuses.size() : 1;

        String ticketNumber = String.format("%03d",++nextTicketNumber);

        ParkingTicket receipt =  busTruck.getTicket();
        receipt.setTicketNumber(ticketNumber);
        receipt.setSpotNumber(availableSpot);

        parkedBuses.add(busTruck);

        return receipt;
    }

    @Override
    public ParkingReceipt unParkBusTruck(String ticketNumber, LocalDateTime unparkTime) throws Exception {

        if(ticketNumber == null || ticketNumber.equals(""))
            throw new IllegalArgumentException("Receipt cannot be null");

        BusTruck parkBusTruck =  parkedBuses.stream()
                .filter(v -> v.getTicket().getTicketNumber().equals(ticketNumber))
                .findFirst()
                .get();

        parkedBuses.remove(parkBusTruck);

        if(parkBusTruck == null)
            throw new Exception(String.format("Vehicle with ticket %s not found",ticketNumber));

        int fee = 0;

        long parkedMinutes =  ChronoUnit.MINUTES.between(parkBusTruck.getTicket().getEntryTime() , unparkTime);
        double parkedHours = (double) parkedMinutes / 60;

        fee = (int)Math.ceil(parkedHours) * feeStructure.get("BusTruck");

        ParkingReceipt r = new ParkingReceipt(String.format("R-%03d", ++nextReceiptNumber),
                parkBusTruck.getTicket().getEntryTime()
                ,unparkTime,fee);

        parkBusTruck.setReceipt(r);

        return parkBusTruck.getReceipt();
    }

}

