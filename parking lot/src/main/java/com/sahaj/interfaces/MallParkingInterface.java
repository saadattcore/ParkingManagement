package com.sahaj.interfaces;

import com.sahaj.model.*;

import java.time.LocalDateTime;

public interface MallParkingInterface {

    ParkingTicket parkMotorcycle(Motorcycle motorcycle) throws Exception;
    ParkingReceipt unParkMotorcycle(String ticketNumber, LocalDateTime unparkTime) throws Exception;
    ParkingTicket parkCarSuv(CarSuv car) throws Exception;
    ParkingReceipt unParkCarSuv(String ticketNumber,LocalDateTime unparkTime) throws Exception;
    ParkingTicket parkBusTruck(BusTruck busTruck) throws Exception;
    ParkingReceipt unParkBusTruck(String ticketNumber, LocalDateTime unparkTime) throws Exception;

}
