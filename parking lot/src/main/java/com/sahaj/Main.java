package com.sahaj;

import com.sahaj.interfaces.AirportParkingInterface;
import com.sahaj.interfaces.MallParkingInterface;
import com.sahaj.interfaces.StadiumParkingInterface;
import com.sahaj.model.*;
import com.sahaj.parkingmanager.AirportParkingLot;
import com.sahaj.parkingmanager.MallParkingLot;
import com.sahaj.parkingmanager.StadiumParkingLot;


import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        testStadiumMotorycycle();
        testStadiumCarSuv();
        testAirportCar();
        testAirportMotorcycle();
        testMallBus();
        testMallCar();
        testMallMotorcycle();

    }

    public static void testStadiumMotorycycle() throws Exception{

        StadiumParkingInterface parking = new StadiumParkingLot(1000, 1500) {
        };

        Motorcycle motorcycle = new Motorcycle("WHJ PKJ",
                new ParkingTicket(LocalDateTime.now()));

        LocalDateTime unParkTime = LocalDateTime.now().plusHours(14).plusMinutes(59);

        ParkingTicket ticket =  parking.parkMotorcycle(motorcycle);
        System.out.println(ticket.toString());
        ParkingReceipt receipt = parking.unParkMotorcycle(ticket.getTicketNumber(),unParkTime);
        System.out.println(receipt.toString());
    }

    public static int testStadiumCarSuv() throws  Exception{
        StadiumParkingInterface parking = new StadiumParkingLot(1000, 1500) {
        };

        CarSuv car = new CarSuv("WHJ PKJ",
                new ParkingTicket(LocalDateTime.now()));

        LocalDateTime unParkTime = LocalDateTime.now().plusHours(13).plusMinutes(5);

        ParkingTicket ticket =  parking.parkCarSuv(car);
        System.out.println(ticket.toString());
        ParkingReceipt receipt = parking.unParkCarSuv(ticket.getTicketNumber(),unParkTime);
        System.out.println(receipt.toString());
        return  receipt.getFee();
    }

    public static int testAirportMotorcycle() throws Exception{
        AirportParkingInterface parking = new AirportParkingLot(1000, 1500) {
        };

        Motorcycle motorcycle = new Motorcycle("WHJ PKJ",
                new ParkingTicket(LocalDateTime.now()));

        LocalDateTime unParkTime = LocalDateTime.now().plusDays(1).plusHours(12);

        ParkingTicket ticket =  parking.parkMotorcycle(motorcycle);
        System.out.println(ticket.toString());
        ParkingReceipt receipt = parking.unParkMotorcycle(ticket.getTicketNumber(),unParkTime);
        System.out.println(receipt.toString());
        return  receipt.getFee();
    }

    public static int testAirportCar() throws Exception{
        AirportParkingInterface parking = new AirportParkingLot(1000, 1500) {
        };

        CarSuv carSuv = new CarSuv("WHJ PKJ",
                new ParkingTicket(LocalDateTime.now()));

        LocalDateTime unParkTime = LocalDateTime.now().plusMinutes(50);

        ParkingTicket ticket =  parking.parkCarSuv(carSuv);
        System.out.println(ticket.toString());
        ParkingReceipt receipt = parking.unParkCarSuv(ticket.getTicketNumber(),unParkTime);
        System.out.println(receipt.toString());
        return  receipt.getFee();
    }

    public static int testMallMotorcycle() throws Exception{
        MallParkingInterface parking = new MallParkingLot(100, 80,10) {
        };

        Motorcycle motorcycle = new Motorcycle("WHJ PKJ",
                new ParkingTicket(LocalDateTime.now()));

        LocalDateTime unParkTime = LocalDateTime.now().plusHours(3).plusMinutes(30);

        ParkingTicket ticket =  parking.parkMotorcycle(motorcycle);
        System.out.println(ticket.toString());
        ParkingReceipt receipt = parking.unParkMotorcycle(ticket.getTicketNumber(),unParkTime);
        System.out.println(receipt.toString());
        return  receipt.getFee();
    }
    public static int testMallCar() throws Exception{
        MallParkingInterface parking = new MallParkingLot(100, 80,10) {
        };

        CarSuv car = new CarSuv("WHJ PKJ",
                new ParkingTicket(LocalDateTime.now()));

        LocalDateTime unParkTime = LocalDateTime.now().plusHours(6).plusMinutes(1);

        ParkingTicket ticket =  parking.parkCarSuv(car);
        System.out.println(ticket.toString());
        ParkingReceipt receipt = parking.unParkCarSuv(ticket.getTicketNumber(),unParkTime);
        System.out.println(receipt.toString());
        return  receipt.getFee();
    }

    public static int testMallBus() throws Exception{
        MallParkingInterface parking = new MallParkingLot(100, 80,10) {
        };

        BusTruck bus = new BusTruck("WHJ PKJ",
                new ParkingTicket(LocalDateTime.now()));

        LocalDateTime unParkTime = LocalDateTime.now().plusHours(1).plusMinutes(59);

        ParkingTicket ticket =  parking.parkBusTruck(bus);
        System.out.println(ticket.toString());
        ParkingReceipt receipt = parking.unParkBusTruck(ticket.getTicketNumber(),unParkTime);
        System.out.println(receipt.toString());
        return  receipt.getFee();
    }

}
