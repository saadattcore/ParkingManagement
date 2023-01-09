package com.sahaj.parkingmanager;

import com.sahaj.interfaces.StadiumParkingInterface;
import com.sahaj.model.*;
import org.w3c.dom.ranges.Range;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Optional;

public class StadiumParkingLot extends ParkingLot implements StadiumParkingInterface
{
    private  Hashtable<String,ArrayList<HourRange>> feeStructure;
    private final int INFINITE = 9999;

    public StadiumParkingLot(int motorCycleSpots, int carSpots){
        super(motorCycleSpots,carSpots,0);

        parkedMotorcycles = new ArrayList<>(motorCycleSpots);
        parkedCars = new ArrayList<>(carSuvSpots);

        feeStructure = new Hashtable<String,ArrayList<HourRange>>();

        ArrayList<HourRange> motorcycleFees = new ArrayList<>();
        motorcycleFees.add(new HourRange(0,4,30));
        motorcycleFees.add(new HourRange(4,12,60));
        motorcycleFees.add(new HourRange(12,INFINITE,100));
        feeStructure.put("Motorcycle",motorcycleFees);

        ArrayList<HourRange> carFee = new ArrayList<>();
        carFee.add(new HourRange(0,4,60));
        carFee.add(new HourRange(4,12,120));
        carFee.add(new HourRange(12,INFINITE,200));
        feeStructure.put("Car",carFee);

    }


    @Override
    public ParkingTicket parkMotorcycle(Motorcycle motorcycle) throws Exception {
        if(motorcycle == null || motorcycle.getTicket() == null)
            throw new IllegalArgumentException("Motorcycle cannot be null");

        if(motorcycle.getTicket() == null)
            throw new IllegalArgumentException("Receipt cannot be null");

        if(parkedMotorcycles.size() == this.motorCycleSpots )
            throw  new IllegalArgumentException("Parking capacity for motorcycles is full");

        int availableSpot = parkedMotorcycles.size() > 0 ? parkedMotorcycles.size() : 1;

        String nextTicketNumber = String.format("%03d",availableSpot);

        ParkingTicket receipt =  motorcycle.getTicket();
        receipt.setTicketNumber(nextTicketNumber);
        receipt.setSpotNumber(availableSpot);

        parkedMotorcycles.add(motorcycle);

        return receipt;
    }

    @Override
    public ParkingReceipt unParkMotorcycle(String ticketNumber, LocalDateTime unparkTime) throws Exception {
        if(ticketNumber == null || ticketNumber.equals(""))
            throw new IllegalArgumentException("Receipt cannot be null");

        Motorcycle parkedMotorcycle =  parkedMotorcycles.stream()
                .filter(v -> v.getTicket().getTicketNumber().equals(ticketNumber))
                .findFirst()
                .get();

        if(parkedMotorcycle == null)
            throw new Exception(String.format("Vehicle with ticket %s not found",ticketNumber));

        int fee = 0;

        double totalTimeInMinutes = ChronoUnit.MINUTES.between(parkedMotorcycle.getTicket().getEntryTime()
                , unparkTime);

        double parkedHours = totalTimeInMinutes / 60;

        ArrayList<HourRange> motorCycleFee =  feeStructure.get("Motorcycle");

        HourRange inifite = motorCycleFee.stream()
                .filter(h-> h.getUpperLimit() == INFINITE)
                .findFirst().get();


        if(parkedHours < inifite.getLowerLimit()){

            for (HourRange range:motorCycleFee) {

                if(parkedHours >= range.getLowerLimit() )
                    fee+= range.getFee();
            }

        }else{
            parkedHours = (int)Math.ceil(parkedHours);

            fee =   motorCycleFee.stream().
                    filter(h -> h.getUpperLimit() != INFINITE)
                    .mapToInt(h->  h.getFee())
                    .sum();
            for (int i = inifite.getLowerLimit(); i < parkedHours; i++){
                fee+= inifite.getFee();
            }
        }

        ParkingReceipt r = new ParkingReceipt(String.format("R-%03d",++nextReceiptNumber),
                parkedMotorcycle.getTicket().getEntryTime(),
                unparkTime,fee
        );

        parkedMotorcycle.setReceipt(r);

        return parkedMotorcycle.getReceipt();
    }

    @Override
    public ParkingTicket parkCarSuv(CarSuv car) throws Exception {
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

        double totalTimeInMinutes = ChronoUnit.MINUTES.between(parkedCar.getTicket().getEntryTime()
                , unparkTime);

        double parkedHours = totalTimeInMinutes / 60;
        ArrayList<HourRange> carSuvFee =  feeStructure.get("Car");


        HourRange inifite = carSuvFee.stream()
                .filter(h-> h.getUpperLimit() == INFINITE)
                .findFirst()
                .orElse(null);

        if(parkedHours < inifite.getLowerLimit()){

            for (HourRange range:carSuvFee) {

                if(parkedHours >= range.getLowerLimit() )
                    fee+= range.getFee();
            }

        }else{

            parkedHours = (int)Math.ceil(parkedHours);

            fee =   carSuvFee.stream().
                    filter(h -> h.getUpperLimit() != INFINITE)
                    .mapToInt(h->  h.getFee())
                    .sum();
            for (int i = inifite.getLowerLimit(); i < parkedHours; i++){
                fee+= inifite.getFee();
            }
        }

        ParkingReceipt r = new ParkingReceipt(String.format("R-%03d",++nextReceiptNumber),
                parkedCar.getTicket().getEntryTime(),
                unparkTime,fee);

        parkedCar.setReceipt(r);

        return parkedCar.getReceipt();
    }
}
